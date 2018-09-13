/*
 * iStockage
 * File: StockController.java
 * Author: 詹晟
 * Created: 2018/9/2
 * Modified: 2018/9/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import java.util.Map;

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
import com.istockage.common.util.PaginationUtil;
import com.istockage.common.util.UrlUtil;
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
	 * 股票交易明細 / 股票庫存明細 - init
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param model Model
	 * @return /WEB-INF/view/stock/list.jsp / /WEB-INF/view/stock/inventory.jsp
	 */
	@RequestMapping(value = "/stock/*", method = RequestMethod.GET)
	public String stockListView(@SessionAttribute(USER) MemberEntity user, Model model) {

		String path = UrlUtil.getPath(request.getServletPath());

		int currentPage = (request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page"));

		int pageRowCount = PAGE_ROW_COUNT_NUMBER;
		int groupRowCount = GROUP_ROW_COUNT_NUMBER;

		Map<String, Object> map = stockService.selectByConditions(user, null,
				PaginationUtil.getFirst(currentPage, pageRowCount), pageRowCount, path);

		int pageCount = PaginationUtil.getPageCount((int) map.get("count"), pageRowCount);

		// 取得當前頁碼的證券帳戶
		model.addAttribute(STOCK_LIST, map.get("list"));

		// 取得分頁資訊
		model.addAllAttributes(PaginationUtil.allAttributes(request.getServletPath(), pageRowCount, pageCount,
				currentPage, groupRowCount));

		return path;
	}

	/**
	 * 新增股票庫存 - init
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param model Model
	 * @return /WEB-INF/view/stock/inventory/add.jsp
	 */
	@RequestMapping(value = "/stock/inventory/add", method = RequestMethod.GET)
	public String stockInventoryAddView(@SessionAttribute(USER) MemberEntity user, Model model) {

		// 新增 form-backing object
		model.addAttribute(STOCK_ENTITY, new StockEntity());

		// 取得選定會員中的所有證券帳戶
		model.addAttribute(SECURITIES_ACCOUNT_LIST, securitiesAccountService.selectBySa_me_id(user.getMe_id()));

		return STOCK_INVENTORY_ADD_VIEW;
	}

	/**
	 * 新增股票庫存 - submit
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param co_no Byte --> 編碼
	 * @param stockEntity StockEntity --> form-backing object
	 * @param bindingResult BindingResult
	 * @return /WEB-INF/view/stock/inventory.jsp
	 */
	@RequestMapping(value = "/stock/inventory/add.do", method = RequestMethod.POST)
	public String stockInventoryAddAction(@SessionAttribute(USER) MemberEntity user, @RequestParam Byte co_no,
			@Valid StockEntity stockEntity, BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") 股票庫存新增失敗 (格式錯誤: "
					+ BindingResultUtil.getFieldErrors(bindingResult) + ")");

			return STOCK_INVENTORY_ADD_VIEW;

		} else {

			stockEntity.setSt_MemberEntity(user);

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

			logger.info("(" + className + "." + methodName + ") 股票庫存新增成功");

			return REDIRECT + STOCK_INVENTORY_VIEW;
		}
	}

}
