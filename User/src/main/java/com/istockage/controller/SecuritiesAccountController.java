/*
 * iStockage
 * File: SecuritiesAccountController.java
 * Author: 詹晟
 * Created: 2018/8/12
 * Modified: 2018/8/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;
import com.istockage.common.util.PaginationUtil;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.SecuritiesAccountEntity;
import com.istockage.model.entity.SecuritiesBrokerBranchEntity;
import com.istockage.model.service.SecuritiesAccountService;
import com.istockage.model.service.SecuritiesBrokerBranchService;
import com.istockage.model.service.SecuritiesBrokerHeadService;

/**
 * securities_account controller
 * 
 * @author 詹晟
 */
@Controller
public class SecuritiesAccountController implements ControllerConstant {

	private static final Logger logger = Logger.getLogger(SecuritiesAccountController.class);

	private String className = this.getClass().getSimpleName();

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注入 SecuritiesBrokerHeadService
	 */
	@Autowired
	private SecuritiesBrokerHeadService securitiesBrokerHeadService;

	/**
	 * 注入 SecuritiesBrokerBranchService
	 */
	@Autowired
	private SecuritiesBrokerBranchService securitiesBrokerBranchService;

	/**
	 * 注入 SecuritiesAccountService
	 */
	@Autowired
	private SecuritiesAccountService securitiesAccountService;

	/**
	 * 證券帳戶 - init
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param model Model
	 * @return /WEB-INF/view/settings/securities-account.jsp
	 */
	@RequestMapping(value = "/settings/securities-account", method = RequestMethod.GET)
	public String settingsSecuritiesAccountView(@SessionAttribute(USER) MemberEntity user, Model model) {

		int currentPage = (request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page"));

		int pageRowCount = PAGE_ROW_COUNT_NUMBER;
		int groupRowCount = GROUP_ROW_COUNT_NUMBER;

		Map<String, Object> map = securitiesAccountService.selectBySa_me_id(user.getMe_id(), currentPage, pageRowCount);

		int pageCount = PaginationUtil.getPageCount((int) map.get("count"), pageRowCount);

		// 取得 ServletPath
		model.addAttribute(SERVLET_PATH, request.getServletPath());

		// 取得當前頁碼的證券帳戶 List
		model.addAttribute(SECURITIES_ACCOUNT_LIST, map.get("list"));

		// 取得分頁資訊
		model.addAllAttributes(PaginationUtil.allAttributes(pageRowCount, pageCount, currentPage, groupRowCount));

		return SETTINGS_SECURITIES_ACCOUNT_VIEW;
	}

	/**
	 * 新增證券帳戶 - init
	 * 
	 * @param model Model
	 * @return /WEB-INF/view/settings/securities-account/add.jsp
	 */
	@RequestMapping(value = "/settings/securities-account/add", method = RequestMethod.GET)
	public String settingsSecuritiesAccountAddView(Model model) {

		// 取得所有證券商 List
		model.addAttribute(SECURITIES_BROKER_HEAD_LIST, securitiesBrokerHeadService.selectByAll());

		// 新增 form-backing object
		model.addAttribute(SECURITIES_ACCOUNT_ENTITY, new SecuritiesAccountEntity());

		return SETTINGS_SECURITIES_ACCOUNT_ADD_VIEW;
	}

	/**
	 * 選定證券商中的所有分公司 - AJAX
	 * 
	 * @param sb_sh_id Integer --> 證券商流水號
	 * @return SecuritiesBrokerBranchEntity JSON
	 */
	@RequestMapping(value = "/settings/securities-account/add/securities-broker-branch-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String settingsSecuritiesAccountAddSecuritiesBrokerBranchListAjax(Integer sb_sh_id) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		List<SecuritiesBrokerBranchEntity> list = securitiesBrokerBranchService.selectBySb_sh_id(sb_sh_id);

		List<SecuritiesBrokerBranchEntity> jsonList = new ArrayList<SecuritiesBrokerBranchEntity>();
		for (SecuritiesBrokerBranchEntity bean : list) {
			SecuritiesBrokerBranchEntity jsonBean = new SecuritiesBrokerBranchEntity();
			jsonBean.setSb_id(bean.getSb_id());
			jsonBean.setSb_name(bean.getSb_name());
			jsonList.add(jsonBean);
		}

		String json = new Gson().toJson(jsonList);

		logger.info("(" + className + "." + methodName + ") JSON = " + json);

		return json;
	}

}
