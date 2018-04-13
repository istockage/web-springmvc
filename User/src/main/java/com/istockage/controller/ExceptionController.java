/*
 * iStockage
 * File: ExceptionController.java
 * Author: 詹晟
 * Created: 2018/3/29
 * Modified: 2018/4/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * exception controller
 * 
 * @author 詹晟
 */
@Controller
public class ExceptionController implements ControllerConstant {

	/**
	 * 找不到網頁 - init
	 * 
	 * @return /WEB-INF/view/error/page-not-found.jsp
	 */
	@RequestMapping(value = "/error/page-not-found", method = RequestMethod.GET)
	public String pageNotFoundView() {

		return ERROR_PAGE_NOT_FOUND_VIEW;
	}

}
