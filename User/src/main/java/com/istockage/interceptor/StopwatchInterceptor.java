/*
 * iStockage
 * File: StopwatchInterceptor.java
 * Author: 詹晟
 * Created: 2018/3/29
 * Modified: 2018/4/13
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

import com.istockage.controller.ControllerConstant;

/**
 * stopwatch interceptor
 * 
 * @author 詹晟
 */
public class StopwatchInterceptor implements HandlerInterceptor, ControllerConstant {

	private static final Logger logger = Logger.getLogger(StopwatchInterceptor.class);

	private long startTime;
	private long endTime;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		startTime = System.currentTimeMillis();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") time start...");

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		endTime = System.currentTimeMillis();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") time stop, time: " + (endTime - startTime)
				+ "ms\n");
	}

}
