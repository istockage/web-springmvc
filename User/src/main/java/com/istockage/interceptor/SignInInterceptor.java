/*
 * iStockage
 * File: SignInInterceptor.java
 * Author: 詹晟
 * Created: 2018/3/30
 * Modified: 2018/3/30
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

import com.istockage.common.constant.ControllerConstant;
import com.istockage.common.util.StringUtil;
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

		String requestPath = StringUtil.getRequestPath(request.getServletPath(), request.getQueryString()); // 請求 path

		if ((MemberEntity) request.getSession().getAttribute(USER) != null) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 已登入，攔截: " + requestPath);

			request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_VIEW).forward(request, response);

			return false;

		} else {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 未登入，放行: " + requestPath);

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