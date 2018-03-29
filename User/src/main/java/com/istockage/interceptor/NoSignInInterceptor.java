/*
 * iStockage
 * File: NoSignInInterceptor.java
 * Author: 詹晟
 * Created: 2018/3/30
 * Modified: 2018/3/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.istockage.common.constant.ControllerConstant;
import com.istockage.common.util.StringUtil;
import com.istockage.model.entity.MemberEntity;

/**
 * no sign in interceptor
 * 
 * @author 詹晟
 */
public class NoSignInInterceptor implements HandlerInterceptor, ControllerConstant {

	private static final Logger logger = Logger.getLogger(NoSignInInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		HttpSession session = request.getSession();
		String next = StringUtil.getRequestPath(request.getServletPath(), request.getQueryString()); // 原請求 path

		MemberEntity user = (MemberEntity) session.getAttribute(USER);

		if (user == null) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 未登入，攔截: " + next + "，跳轉至: "
					+ MEMBER_SIGN_IN_VIEW);

			// 將原請求 path，放入 Session
			session.setAttribute(NEXT_VIEW, MEMBER_SIGN_OUT_DO.equals(next) ? INDEX_VIEW : next);

			response.sendRedirect(request.getContextPath() + SLASH + MEMBER_SIGN_IN_VIEW);

			return false;

		} else {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 已登入，使用者: " + user.getMe_email()
					+ "，放行: " + next);

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
