/*
 * iStockage
 * File: ActionInterceptor.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/3/29
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

import com.istockage.common.constant.ControllerConstant;
import com.istockage.common.util.StringUtil;
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
	 * 注入 MemberLogService
	 */
	@Autowired
	private MemberLogService memberLogService;

	/**
	 * 注入 UserPathService
	 */
	@Autowired
	private UserPathService userPathService;

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		MemberEntity session_MemberEntity = (MemberEntity) request.getSession().getAttribute(USER);
		MemberEntity model_MemberEntity = (MemberEntity) modelAndView.getModel().get(USER);

		if (session_MemberEntity == null && model_MemberEntity == null) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 不寫入日誌");

			return;
		}

		String servletPath = request.getServletPath(); // /path

		UserPathEntity userPathEntity = userPathService.selectByUp_path(StringUtil.getExtension(servletPath),
				StringUtil.getPath(servletPath));

		MemberLogEntity memberLogEntity = new MemberLogEntity();
		memberLogEntity.setMl_MemberEntity((session_MemberEntity == null) ? model_MemberEntity : session_MemberEntity);
		memberLogEntity.setMl_UserPathEntity(userPathEntity);
		memberLogEntity.setMl_ip(request.getRemoteAddr());
		memberLogService.insert(memberLogEntity);

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 寫入日誌: " + userPathEntity.getUp_name());
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
