/*
 * iStockage
 * File: StockController.java
 * Author: 詹晟
 * Created: 2018/9/2
 * Modified: 2018/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.istockage.common.json.GsonReader;
import com.istockage.common.json.StockExchangeReportBean;
import com.istockage.common.util.BindingResultUtil;
import com.istockage.common.util.PaginationUtil;
import com.istockage.common.util.StockUtil;
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

	private static final Logger logger = Logger.getLogger(StockController.class);

	private String className = this.getClass().getSimpleName();

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注入 CodeCategoryService
	 */
	@Autowired
	private CodeCategoryService codeCategoryService;

	/**
	 * 注入 SecuritiesAccountService
	 */
	@Autowired
	private SecuritiesAccountService securitiesAccountService;

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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/stock/*", method = RequestMethod.GET)
	public String stockListOrInventoryView(@SessionAttribute(USER) MemberEntity user, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String path = UrlUtil.getPath(request.getServletPath());

		int currentPage;
		try {
			String pageParameter = request.getParameter("page");
			currentPage = (pageParameter == null) ? 1 : Integer.parseInt(pageParameter);

		} catch (NumberFormatException e) {

			return ERROR_PAGE_NOT_FOUND_VIEW;
		}

		int pageRowCount = PAGE_ROW_COUNT_NUMBER;
		int groupRowCount = GROUP_ROW_COUNT_NUMBER;

		Map<String, Object> map = stockService.selectByConditions(user, null, path,
				PaginationUtil.getFirst(currentPage, pageRowCount), pageRowCount);

		int pageCount = PaginationUtil.getPageCount((int) map.get("count"), pageRowCount);

		List<StockEntity> stockList = (List<StockEntity>) map.get("list");

		// 取得當前頁碼的證券帳戶
		model.addAttribute(STOCK_LIST, stockList);

		if (STOCK_INVENTORY_VIEW.equals(path)) {

			Set<String> stockNoSet = new HashSet<String>();
			for (StockEntity stockEntity : stockList) {
				stockNoSet.add(stockEntity.getSt_no());
			}

			Map<String, Float> stockPriceMap = new HashMap<String, Float>();
			for (String st_no : stockNoSet) {
				try {
					StockExchangeReportBean stockExchangeReportBean = GsonReader
							.getStockExchangeReport(new SimpleDateFormat("yyyyMMdd").format(new Date()), st_no);

					stockPriceMap.put(st_no, Float.valueOf(stockExchangeReportBean.getData()
							.get(stockExchangeReportBean.getData().size() - 1).get(6)));

				} catch (Exception e) {

					logger.error("(" + className + "." + methodName + ") 股票交易報告請求失敗 (代號: " + st_no + ")");

					return path;
				}
			}

			logger.info("(" + className + "." + methodName + ") 股票交易報告請求成功");

			for (StockEntity stockEntity : stockList) {
				if (stockEntity.getSt_sell_price() == null) {
					Float st_sell_price = stockPriceMap.get(stockEntity.getSt_no());
					stockEntity.setSt_sell_price(st_sell_price);
					stockEntity.setSt_sell_delivery(StockUtil.getSt_sell_delivery(st_sell_price, stockEntity));
				}
			}
		}

		// 取得分頁資訊
		model.addAllAttributes(PaginationUtil.allAttributes(path, pageRowCount, pageCount, currentPage, groupRowCount));

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
