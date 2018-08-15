/*
 * iStockage
 * File: AccountController.java
 * Author: 詹晟
 * Created: 2018/8/12
 * Modified: 2018/8/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	 * 注入 AccountService
	 */
	@Autowired
	private AccountService accountService;

	/**
	 * 證券帳戶 - init
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param page Integer --> 當前頁碼
	 * @param model Model
	 * @return /WEB-INF/view/settings/stock-account.jsp
	 */
	@RequestMapping(value = "/settings/stock-account", method = RequestMethod.GET)
	public String stockAccountView(@SessionAttribute(USER) MemberEntity user, @RequestParam Integer page, Model model) {

		Map<String, Object> map = accountService.selectByAc_me_id(user.getMe_id(), page, 10);

		// 取得當前頁碼的證券帳戶 List
		model.addAttribute("accountList", map.get("list"));

		return SETTINGS_STOCK_ACCOUNT_VIEW;
	}

}
