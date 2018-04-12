/*
 * iStockage
 * File: PageNotFoundException.java
 * Author: 詹晟
 * Created: 2018/4/12
 * Modified: 2018/4/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.exception;

import org.apache.log4j.Logger;

/**
 * page not found exception
 * 
 * @author 詹晟
 */
public class PageNotFoundException extends NullPointerException {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(PageNotFoundException.class);

	public PageNotFoundException() {

	}

	public PageNotFoundException(String requestPath) {

		super("找不到這個頁面: " + requestPath);

		logger.error("找不到這個頁面: " + requestPath);
	}

}
