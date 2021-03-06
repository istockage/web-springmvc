/*
 * iStockage
 * File: ActionInterceptor.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/9/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.istockage.common.util.UrlUtil;
import com.istockage.controller.ControllerConstant;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.MemberLogEntity;
import com.istockage.model.entity.UserPathEntity;
import com.istockage.model.service.MemberLogService;
import com.istockage.model.service.UserPathService;

/**
 * action interceptor
 * 
 * @author 詹晟
 */
public class ActionInterceptor implements HandlerInterceptor, ControllerConstant {

	private static final Logger logger = Logger.getLogger(ActionInterceptor.class);

	/**
	 * 注入 UserPathService
	 */
	@Autowired
	private UserPathService userPathService;

	/**
	 * 注入 MemberLogService
	 */
	@Autowired
	private MemberLogService memberLogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		String servletPath = request.getServletPath(); // /path
		String requestPath = UrlUtil.getRequestPath(servletPath, request.getQueryString()); // 請求 path
		UserPathEntity userPathEntity = userPathService.selectByUp_path(UrlUtil.getPath(servletPath));

		if (userPathEntity == null) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end (攔截: " + requestPath + ")");

			request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_VIEW).forward(request, response);

			return false;
		}

		if (handlerMethodName.indexOf("Action") == -1) { // 經過 GET

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end (攔截: " + requestPath + ")");

			request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_VIEW).forward(request, response);

			return false;
		}

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") end (放行: " + requestPath + ")");

		request.setAttribute(USER_PATH_ENTITY, userPathEntity);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		UserPathEntity userPathEntity = (UserPathEntity) request.getAttribute(USER_PATH_ENTITY);

		if (!OK.equals((String) request.getAttribute(MEMBER_LOG_KEY))) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end (不寫入日誌)");

			return;
		}

		MemberEntity request_MemberEntity = (MemberEntity) request.getAttribute(MEMBER_ENTITY);
		MemberEntity session_MemberEntity = (MemberEntity) request.getSession().getAttribute(USER);
		MemberEntity model_MemberEntity = (MemberEntity) modelAndView.getModel().get(USER);

		MemberEntity memberEntity = null;

		if (request_MemberEntity != null) {

			memberEntity = request_MemberEntity;

		} else if (session_MemberEntity != null || model_MemberEntity != null) {

			memberEntity = (session_MemberEntity == null) ? model_MemberEntity : session_MemberEntity;

		} else {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end (不寫入日誌)");

			return;
		}

		MemberLogEntity memberLogEntity = new MemberLogEntity();
		memberLogEntity.setMl_MemberEntity(memberEntity);
		memberLogEntity.setMl_UserPathEntity(userPathEntity);
		memberLogEntity.setMl_ip(request.getRemoteAddr());
		memberLogService.insert(memberLogEntity);

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") end (寫入日誌, 動作: " + userPathEntity.getUp_name()
				+ ")");
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
