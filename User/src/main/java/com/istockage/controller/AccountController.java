/*
 * iStockage
 * File: AccountController.java
 * Author: 詹晟
 * Created: 2018/8/12
 * Modified: 2018/8/18
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
import com.istockage.model.service.AccountService;

/**
 * account controller
 * 
 * @author 詹晟
 */
@Controller
public class AccountController implements ControllerConstant {

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注入 AccountService
	 */
	@Autowired
	private AccountService accountService;

	/**
	 * 證券帳戶 - init
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param model Model
	 * @return /WEB-INF/view/settings/stock-account.jsp
	 */
	@RequestMapping(value = "/settings/stock-account", method = RequestMethod.GET)
	public String stockAccountView(@SessionAttribute(USER) MemberEntity user, Model model) {

		Integer page = (request.getParameter("page") == null) ? 1 : Integer.valueOf(request.getParameter("page"));

		int pageRowCount = PAGE_ROW_COUNT_NUMBER;
		int groupRowCount = GROUP_ROW_COUNT_NUMBER;

		Map<String, Object> map = accountService.selectByAc_me_id(user.getMe_id(), page, pageRowCount);

		int pageCount = PaginationUtil.getPageCount((int) map.get("count"), pageRowCount);

		// 取得當前頁碼的證券帳戶 List
		model.addAttribute("accountList", map.get("list"));

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, pageRowCount);

		// 取得總頁數
		model.addAttribute(PAGE_COUNT, pageCount);

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得每群最大頁數
		model.addAttribute(GROUP_ROW_COUNT, groupRowCount);

		// 取得總群數
		model.addAttribute(GROUP_COUNT, PaginationUtil.getGroupCount(pageCount, groupRowCount));

		// 取得當前群序
		model.addAttribute(CURRENT_GROUP, PaginationUtil.getCurrentGroup(page, groupRowCount));

		// 取得當前群序起始頁碼
		model.addAttribute(CURRENT_GROUP_BEGIN, PaginationUtil.getCurrentGroupBegin(page, groupRowCount));

		// 取得當前群序結束頁碼
		model.addAttribute(CURRENT_GROUP_END, PaginationUtil.getCurrentGroupEnd(pageCount, page, groupRowCount));

		return SETTINGS_STOCK_ACCOUNT_VIEW;
	}

}
