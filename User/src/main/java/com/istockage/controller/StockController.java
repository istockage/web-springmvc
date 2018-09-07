/*
 * iStockage
 * File: StockController.java
 * Author: 詹晟
 * Created: 2018/9/2
 * Modified: 2018/9/7
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.istockage.common.editor.SecuritiesAccountEntityPropertyEditor;
import com.istockage.common.util.BindingResultUtil;
import com.istockage.model.entity.CodeCategoryEntity;
import com.istockage.model.entity.CodeEntity;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.SecuritiesAccountEntity;
import com.istockage.model.entity.StockEntity;
import com.istockage.model.service.CodeCategoryService;
import com.istockage.model.service.SecuritiesAccountService;
import com.istockage.model.service.StockService;

/**
 * stock controller
 * 
 * @author 詹晟
 */
@Controller
public class StockController implements ControllerConstant {

	private static final Logger logger = Logger.getLogger(SecuritiesAccountController.class);

	private String className = this.getClass().getSimpleName();

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
	 * 注入 CodeCategoryService
	 */
	@Autowired
	private CodeCategoryService codeCategoryService;

	/**
	 * 注入 StockService
	 */
	@Autowired
	private StockService stockService;

	/**
	 * form-backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd HH:mm"));
		webDataBinder.registerCustomEditor(SecuritiesAccountEntity.class, new SecuritiesAccountEntityPropertyEditor());
	}

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

	/**
	 * 新增股票交易 - submit
	 * 
	 * @param co_no Byte --> 編碼
	 * @param stockEntity StockEntity --> form-backing object
	 * @param bindingResult BindingResult
	 * @return /WEB-INF/view/stock/list.jsp
	 */
	@RequestMapping(value = "/stock/list/add.do", method = RequestMethod.POST)
	public String stockListAddAction(@RequestParam Byte co_no, @Valid StockEntity stockEntity,
			BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") 股票交易新增失敗，格式錯誤: "
					+ BindingResultUtil.getFieldErrors(bindingResult));

			return STOCK_LIST_ADD_VIEW;

		} else {

			CodeCategoryEntity codeCategoryEntity = codeCategoryService.selectByCc_name(STOCK_TYPE_CODE_CATEGORY);

			stockEntity.setSt_CodeCategoryEntity(codeCategoryEntity);

			for (CodeEntity codeEntity : codeCategoryEntity.getCc_CodeEntity()) {
				if (co_no.equals(codeEntity.getCo_no())) {
					stockEntity.setSt_CodeEntity(codeEntity);
					break;
				}
			}

			stockService.insert(stockEntity);

			request.setAttribute(MEMBER_LOG_KEY, OK);

			logger.info("(" + className + "." + methodName + ") 股票交易新增成功");

			return REDIRECT + STOCK_LIST_VIEW;
		}
	}

}
