/*
 * iStockage
 * File: StockController.java
 * Author: 詹晟
 * Created: 2018/9/2
 * Modified: 2018/9/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.StockEntity;
import com.istockage.model.service.SecuritiesAccountService;

/**
 * stock controller
 * 
 * @author 詹晟
 */
@Controller
public class StockController implements ControllerConstant {

	/**
	 * 注入 SecuritiesAccountService
	 */
	@Autowired
	private SecuritiesAccountService securitiesAccountService;

	/**
	 * 股票統計圖表 - init
	 * 
	 * @return /WEB-INF/view/stock/chart.jsp
	 */
	@RequestMapping(value = "/stock/chart", method = RequestMethod.GET)
	public String stockChartView() {

		return STOCK_CHART_VIEW;
	}

	/**
	 * 股票交易明細 - init
	 * 
	 * @return /WEB-INF/view/stock/list.jsp
	 */
	@RequestMapping(value = "/stock/list", method = RequestMethod.GET)
	public String stockListView() {

		return STOCK_LIST_VIEW;
	}

	/**
	 * 新增股票交易 - init
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param model Model
	 * @return /WEB-INF/view/stock/list/add.jsp
	 */
	@RequestMapping(value = "/stock/list/add", method = RequestMethod.GET)
	public String stockListAddView(@SessionAttribute(USER) MemberEntity user, Model model) {

		// 新增 form-backing object
		model.addAttribute(STOCK_ENTITY, new StockEntity());

		// 取得選定會員中的所有證券帳戶
		model.addAttribute(SECURITIES_ACCOUNT_LIST, securitiesAccountService.selectBySa_me_id(user.getMe_id()));

		return STOCK_LIST_ADD_VIEW;
	}

}
