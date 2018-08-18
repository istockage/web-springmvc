/*
 * iStockage
 * File: SecuritiesAccountController.java
 * Author: 詹晟
 * Created: 2018/8/12
 * Modified: 2018/8/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.istockage.common.util.PaginationUtil;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.service.SecuritiesAccountService;

/**
 * securities_account controller
 * 
 * @author 詹晟
 */
@Controller
public class SecuritiesAccountController implements ControllerConstant {

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注入 SecuritiesAccountService
	 */
	@Autowired
	private SecuritiesAccountService securitiesAccountService;

	/**
	 * 證券帳戶 - init
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param model Model
	 * @return /WEB-INF/view/settings/securities-account.jsp
	 */
	@RequestMapping(value = "/settings/securities-account", method = RequestMethod.GET)
	public String settingsSecuritiesAccountView(@SessionAttribute(USER) MemberEntity user, Model model) {

		int currentPage = (request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page"));

		int pageRowCount = PAGE_ROW_COUNT_NUMBER;
		int groupRowCount = GROUP_ROW_COUNT_NUMBER;

		Map<String, Object> map = securitiesAccountService.selectBySa_me_id(user.getMe_id(), currentPage, pageRowCount);

		int pageCount = PaginationUtil.getPageCount((int) map.get("count"), pageRowCount);

		// 取得 ServletPath
		model.addAttribute(SERVLET_PATH, request.getServletPath());

		// 取得當前頁碼的證券帳戶 List
		model.addAttribute(SECURITIES_ACCOUNT_LIST, map.get("list"));

		// 取得分頁資訊
		model.addAllAttributes(PaginationUtil.allAttributes(pageRowCount, pageCount, currentPage, groupRowCount));

		return SETTINGS_SECURITIES_ACCOUNT_VIEW;
	}

}
