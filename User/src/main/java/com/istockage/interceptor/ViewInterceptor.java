/*
 * iStockage
 * File: ViewInterceptor.java
 * Author: 詹晟
 * Created: 2018/3/29
 * Modified: 2018/4/16
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

import com.istockage.common.util.StringUtil;
import com.istockage.controller.ControllerConstant;
import com.istockage.model.service.UserPathService;

/**
 * view interceptor
 * 
 * @author 詹晟
 */
public class ViewInterceptor implements HandlerInterceptor, ControllerConstant {

	private static final Logger logger = Logger.getLogger(ViewInterceptor.class);

	/**
	 * 注入 UserPathService
	 */
	@Autowired
	private UserPathService userPathService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		String servletPath = request.getServletPath(); // /path
		String requestPath = StringUtil.getRequestPath(servletPath, request.getQueryString()); // 請求 path

		if (userPathService.selectByUp_path(VIEW, StringUtil.getPath(servletPath)) == null) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 攔截: " + requestPath);

			request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_VIEW).forward(request, response);

			return false;
		}

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 放行: " + requestPath);

		request.setAttribute(REQUEST_PATH, requestPath);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
