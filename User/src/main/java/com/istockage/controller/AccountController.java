/*
 * iStockage
 * File: AccountController.java
 * Author: 詹晟
 * Created: 2018/8/12
 * Modified: 2018/8/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * account controller
 * 
 * @author 詹晟
 */
@Controller
public class AccountController implements ControllerConstant {

	/**
	 * 證券帳戶 - init
	 * 
	 * @return /WEB-INF/view/settings/stock-account.jsp
	 */
	@RequestMapping(value = "/settings/stock-account", method = RequestMethod.GET)
	public String stockAccountView() {

		return SETTINGS_STOCK_ACCOUNT_VIEW;
	}

}
