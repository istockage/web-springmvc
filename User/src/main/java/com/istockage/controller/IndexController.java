/*
 * iStockage
 * File: IndexController.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/10/4
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.service.StockService;

/**
 * index controller
 * 
 * @author 詹晟
 */
@Controller
public class IndexController implements ControllerConstant {

	private static final Logger logger = Logger.getLogger(IndexController.class);

	private String className = this.getClass().getSimpleName();

	/**
	 * 注入 StockService
	 */
	@Autowired
	private StockService stockService;

	/**
	 * 首頁 - init
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @return /WEB-INF/view/index.jsp
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexView(@SessionAttribute(USER) MemberEntity user) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		String json = gson.toJson(stockService.selectByConditions(user, null));

		logger.info("(" + className + "." + methodName + ") JSON = " + json);

		return INDEX_VIEW;
	}

}
