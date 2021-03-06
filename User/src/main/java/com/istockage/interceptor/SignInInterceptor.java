/*
 * iStockage
 * File: SignInInterceptor.java
 * Author: 詹晟
 * Created: 2018/3/30
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
import com.istockage.model.entity.MemberEntity;

/**
 * sign in interceptor
 * 
 * @author 詹晟
 */
public class SignInInterceptor implements HandlerInterceptor, ControllerConstant {

	private static final Logger logger = Logger.getLogger(SignInInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		MemberEntity user = (MemberEntity) request.getSession().getAttribute(USER);

		String requestPath = UrlUtil.getRequestPath(request.getServletPath(), request.getQueryString()); // 請求 path

		if (user != null) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end (已登入, 攔截: " + requestPath + ", 使用者: "
					+ user.getMe_no() + ")");

			response.sendRedirect(request.getContextPath() + SLASH + INDEX_VIEW);

			return false;

		} else {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end (未登入, 放行: " + requestPath + ")");

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
