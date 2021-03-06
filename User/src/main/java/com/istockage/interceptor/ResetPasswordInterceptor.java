/*
 * iStockage
 * File: ResetPasswordInterceptor.java
 * Author: 詹晟
 * Created: 2018/4/8
 * Modified: 2018/9/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.istockage.common.util.UrlUtil;
import com.istockage.controller.ControllerConstant;

/**
 * reset password interceptor
 * 
 * @author 詹晟
 */
public class ResetPasswordInterceptor implements HandlerInterceptor, ControllerConstant {

	private static final Logger logger = Logger.getLogger(ResetPasswordInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		String requestPath = UrlUtil.getRequestPath(request.getServletPath(), request.getQueryString()); // 請求 path

		if ((String) request.getSession().getAttribute(SESSION_MEMBER_EMAIL) == null) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end (攔截: " + requestPath + ")");

			request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_VIEW).forward(request, response);

			return false;

		} else {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end (放行: " + requestPath + ")");

			return true;
		}
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
