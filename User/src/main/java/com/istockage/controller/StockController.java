/*
 * iStockage
 * File: StockController.java
 * Author: 詹晟
 * Created: 2018/9/2
 * Modified: 2018/10/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import static com.istockage.common.util.BindingResultUtil.getFieldErrors;
import static com.istockage.common.util.PaginationUtil.allAttributes;
import static com.istockage.common.util.PaginationUtil.getFirst;
import static com.istockage.common.util.PaginationUtil.getPageCount;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.istockage.common.editor.SecuritiesAccountEntityPropertyEditor;
import com.istockage.common.json.GsonReader;
import com.istockage.common.json.StockExchangeReportBean;
import com.istockage.common.util.StockUtil;
import com.istockage.model.entity.CodeCategoryEntity;
import com.istockage.model.entity.CodeEntity;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.SecuritiesAccountEntity;
import com.istockage.model.entity.SecuritiesEntity;
import com.istockage.model.entity.StockEntity;
import com.istockage.model.entity.UserPathEntity;
import com.istockage.model.service.CodeCategoryService;
import com.istockage.model.service.CodeService;
import com.istockage.model.service.SecuritiesAccountService;
import com.istockage.model.service.SecuritiesService;
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
	 * 注入 CodeService
	 */
	@Autowired
	private CodeService codeService;

	/**
	 * 注入 SecuritiesService
	 */
	@Autowired
	private SecuritiesService securitiesService;

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

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		logger.info("(" + className + "." + methodName + ") start");

		UserPathEntity userPathEntity = (UserPathEntity) request.getAttribute(USER_PATH_ENTITY);
		String up_name = userPathEntity.getUp_name();

		// TODO
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		String json = gson.toJson(securitiesService.selectLikeBySe_noOrSe_name("23"));
		System.out.println(json);

		logger.info("(" + className + "." + methodName + ") end (成功: " + up_name + ")");

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

		logger.info("(" + className + "." + methodName + ") start");

		UserPathEntity userPathEntity = (UserPathEntity) request.getAttribute(USER_PATH_ENTITY);
		String up_path = userPathEntity.getUp_path();
		String up_name = userPathEntity.getUp_name();

		int currentPage;
		try {
			String pageParameter = request.getParameter("page");
			currentPage = (pageParameter == null) ? 1 : Integer.parseInt(pageParameter);

		} catch (NumberFormatException e) {

			return ERROR_PAGE_NOT_FOUND_VIEW;
		}

		int pageRowCount = PAGE_ROW_COUNT_NUMBER;
		int groupRowCount = GROUP_ROW_COUNT_NUMBER;

		Map<String, Object> map = stockService.selectByConditions(user, null, up_path,
				getFirst(currentPage, pageRowCount), pageRowCount);

		int pageCount = getPageCount((int) map.get("count"), pageRowCount);

		List<StockEntity> stockList = (List<StockEntity>) map.get("list");

		// 取得當前頁碼的證券帳戶
		model.addAttribute(STOCK_LIST, stockList);

		if (STOCK_INVENTORY_VIEW.equals(up_path)) {

			Set<String> stockNoSet = new HashSet<String>();
			for (StockEntity stockEntity : stockList) {
				stockNoSet.add(stockEntity.getSt_SecuritiesEntity().getSe_no());
			}

			Map<String, Float> stockPriceMap = new HashMap<String, Float>();
			for (String st_no : stockNoSet) {
				try {
					StockExchangeReportBean stockExchangeReportBean = GsonReader
							.getStockExchangeReport(new SimpleDateFormat("yyyyMMdd").format(new Date()), st_no);

					stockPriceMap.put(st_no, Float.valueOf(stockExchangeReportBean.getData()
							.get(stockExchangeReportBean.getData().size() - 1).get(6)));

				} catch (Exception e) {

					logger.error("(" + className + "." + methodName + ") halfway (失敗: 股票交易報告, 代號: " + st_no + ")");

					return up_path;
				}
			}

			logger.info("(" + className + "." + methodName + ") halfway (成功: 股票交易報告)");

			for (StockEntity stockEntity : stockList) {
				if (stockEntity.getSt_sell_price() == null) {
					Float st_sell_price = stockPriceMap.get(stockEntity.getSt_SecuritiesEntity().getSe_no());
					stockEntity.setSt_sell_price(st_sell_price);
					stockEntity.setSt_sell_delivery(StockUtil.getSt_sell_delivery(st_sell_price, stockEntity));
				}
			}
		}

		// 取得分頁資訊
		model.addAllAttributes(allAttributes(up_path, pageRowCount, pageCount, currentPage, groupRowCount));

		logger.info("(" + className + "." + methodName + ") end (成功: " + up_name + ")");

		return up_path;
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
	 * @param co_no Byte --> code 編號
	 * @param stockEntity StockEntity --> form-backing object
	 * @param bindingResult BindingResult
	 * @return /WEB-INF/view/stock/inventory.jsp
	 */
	@RequestMapping(value = "/stock/inventory/add.do", method = RequestMethod.POST)
	public String stockInventoryAddAction(@SessionAttribute(USER) MemberEntity user, @RequestParam Byte co_no,
			@Valid StockEntity stockEntity, BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		logger.info("(" + className + "." + methodName + ") start");

		UserPathEntity userPathEntity = (UserPathEntity) request.getAttribute(USER_PATH_ENTITY);
		String up_name = userPathEntity.getUp_name();

		CodeCategoryEntity codeCategoryEntity = codeCategoryService.selectByCc_no(STOCK_TYPE_CODE_CATEGORY);
		CodeEntity codeEntity = codeService.selectByCodeId(codeCategoryEntity, co_no);
		String se_no = stockEntity.getSt_SecuritiesEntity().getSe_no();
		SecuritiesEntity securitiesEntity = securitiesService.selectBySe_no(se_no);

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") end (失敗: " + up_name + ", 格式錯誤: "
					+ getFieldErrors(bindingResult) + ")");

			return STOCK_INVENTORY_ADD_VIEW;

		} else if (codeEntity == null) {

			logger.error("(" + className + "." + methodName + ") end (失敗: " + up_name + ", "
					+ codeCategoryEntity.getCc_name() + "編號錯誤: " + co_no + ")");

			return STOCK_INVENTORY_ADD_VIEW;

		} else if (securitiesEntity == null) {

			logger.error("(" + className + "." + methodName + ") end (失敗: " + up_name + ", 股票代號錯誤: " + se_no + ")");

			return STOCK_INVENTORY_ADD_VIEW;

		} else {

			stockEntity.setSt_MemberEntity(user);
			stockEntity.setSt_SecuritiesEntity(securitiesEntity);
			stockEntity.setSt_CodeEntity(codeEntity);
			stockService.insert(stockEntity);

			request.setAttribute(MEMBER_LOG_KEY, OK);

			logger.info("(" + className + "." + methodName + ") end (成功: " + up_name + ")");

			return REDIRECT + STOCK_INVENTORY_VIEW;
		}
	}

	/**
	 * 股票交易明細 - AJAX
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @return StockEntity JSON
	 */
	@RequestMapping(value = "/stock/list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public @ResponseBody String stockListAjax(@SessionAttribute(USER) MemberEntity user) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		logger.info("(" + className + "." + methodName + ") start");

		GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss");
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		Map<String, List<StockEntity>> map = new HashMap<String, List<StockEntity>>();
		map.put("stocks", stockService.selectByConditions(user, null));

		String json = gson.toJson(map);

		logger.info("(" + className + "." + methodName + ") end (JSON = " + json + ")");

		return json;
	}

	/**
	 * 符合的所有股票 - AJAX
	 * 
	 * @param search String --> 股票代號或名稱
	 * @return SecuritiesEntity JSON
	 */
	@RequestMapping(value = "/stock/inventory/securities-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public @ResponseBody String stockInventorySecuritiesListAjax(String search) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		logger.info("(" + className + "." + methodName + ") start");

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		Map<String, List<SecuritiesEntity>> map = new HashMap<String, List<SecuritiesEntity>>();
		map.put("securities", securitiesService.selectLikeBySe_noOrSe_name(search));

		String json = gson.toJson(map);

		logger.info("(" + className + "." + methodName + ") end (JSON = " + json + ")");

		return json;
	}

}
