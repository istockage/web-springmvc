/*
 * iStockage
 * File: IndexController.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/3/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.istockage.common.constant.ControllerConstant;

/**
 * index controller
 * 
 * @author 詹晟
 */
@Controller
public class IndexController implements ControllerConstant {

	/**
	 * 首頁 - init
	 * 
	 * @return /WEB-INF/view/index.jsp
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexView() {

		return INDEX_VIEW;
	}

}
