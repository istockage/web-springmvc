/*
 * iStockage
 * File: SecuritiesAccountController.java
 * Author: 詹晟
 * Created: 2018/8/12
 * Modified: 2018/9/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;
import com.istockage.common.editor.SecuritiesBrokerBranchEntityPropertyEditor;
import com.istockage.common.editor.SecuritiesBrokerHeadEntityPropertyEditor;
import com.istockage.common.util.PaginationUtil;
import com.istockage.exception.PageNotFoundException;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.SecuritiesAccountEntity;
import com.istockage.model.entity.SecuritiesBrokerBranchEntity;
import com.istockage.model.entity.SecuritiesBrokerHeadEntity;
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
	 * form-backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(SecuritiesBrokerHeadEntity.class,
				new SecuritiesBrokerHeadEntityPropertyEditor());
		webDataBinder.registerCustomEditor(SecuritiesBrokerBranchEntity.class,
				new SecuritiesBrokerBranchEntityPropertyEditor());
	}

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

		// 取得當前頁碼的證券帳戶
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

		// 新增 form-backing object
		model.addAttribute(SECURITIES_ACCOUNT_ENTITY, new SecuritiesAccountEntity());

		// 取得所有證券商
		model.addAttribute(SECURITIES_BROKER_HEAD_LIST, securitiesBrokerHeadService.selectByAll());

		return SETTINGS_SECURITIES_ACCOUNT_ADD_VIEW;
	}

	/**
	 * 新增證券帳戶 - submit
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param securitiesAccountEntity SecuritiesAccountEntity --> form-backing
	 *        object
	 * @param bindingResult BindingResult
	 * @return /WEB-INF/view/settings/securities-account.jsp
	 */
	@RequestMapping(value = "/settings/securities-account/add.do", method = RequestMethod.POST)
	public String settingsSecuritiesAccountAddAction(@SessionAttribute(USER) MemberEntity user,
			@Valid SecuritiesAccountEntity securitiesAccountEntity, BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") 證券帳戶新增失敗，格式錯誤");

			return SETTINGS_SECURITIES_ACCOUNT_ADD_VIEW;

		} else {

			securitiesAccountEntity.setSa_MemberEntity(user);
			securitiesAccountService.insert(securitiesAccountEntity);

			request.setAttribute(MEMBER_LOG_KEY, OK);
			logger.info("(" + className + "." + methodName + ") 證券帳戶新增成功");

			return REDIRECT + SETTINGS_SECURITIES_ACCOUNT_VIEW;
		}
	}

	/**
	 * 編輯證券帳戶 - init
	 * 
	 * @param securitiesAccountEntity_sa_id SecuritiesAccountEntity --> form-backing
	 *        object --> GET --> sa_id
	 * @param model Model
	 * @return /WEB-INF/view/settings/securities-account/edit.jsp
	 */
	@RequestMapping(value = "/settings/securities-account/edit", method = RequestMethod.GET)
	public String settingsSecuritiesAccountEditView(SecuritiesAccountEntity securitiesAccountEntity_sa_id,
			Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String requestPath = (String) request.getAttribute(REQUEST_PATH);

		SecuritiesAccountEntity securitiesAccountEntity;
		try {
			securitiesAccountEntity = securitiesAccountService.selectBySa_id(securitiesAccountEntity_sa_id.getSa_id());

			if (securitiesAccountEntity == null) {

				throw new PageNotFoundException(requestPath);
			}
		} catch (PageNotFoundException e) {

			return ERROR_PAGE_NOT_FOUND_VIEW;

		} catch (IllegalArgumentException e) {

			logger.error("(" + className + "." + methodName + ") " + "找不到這個頁面: " + requestPath);

			return ERROR_PAGE_NOT_FOUND_VIEW;
		}

		// 使表單回填 SecuritiesAccountEntity 內所有資料
		model.addAttribute(SECURITIES_ACCOUNT_ENTITY, securitiesAccountEntity);

		// 取得所有證券商
		model.addAttribute(SECURITIES_BROKER_HEAD_LIST, securitiesBrokerHeadService.selectByAll());

		// 取得選定證券商中的所有分公司
		model.addAttribute(SECURITIES_BROKER_BRANCH_LIST, securitiesBrokerBranchService
				.selectBySb_sh_id(securitiesAccountEntity.getSa_SecuritiesBrokerHeadEntity().getSh_id()));

		return SETTINGS_SECURITIES_ACCOUNT_EDIT_VIEW;
	}

	/**
	 * 編輯證券帳戶 - submit
	 * 
	 * @param updatedEntity SecuritiesAccountEntity --> form-backing object
	 * @param bindingResult BindingResult
	 * @return /WEB-INF/view/settings/securities-account/edit.jsp
	 */
	@RequestMapping(value = "/settings/securities-account/edit", method = RequestMethod.POST)
	public String settingsSecuritiesAccountEditAction(
			@Valid @ModelAttribute(SECURITIES_ACCOUNT_ENTITY) SecuritiesAccountEntity updatedEntity,
			BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") 證券帳戶編輯失敗，格式錯誤");

			return SETTINGS_SECURITIES_ACCOUNT_EDIT_VIEW;

		} else {

			securitiesAccountService.update(updatedEntity);

			request.setAttribute(MEMBER_LOG_KEY, OK);
			logger.info("(" + className + "." + methodName + ") 證券帳戶編輯成功");

			return REDIRECT + SETTINGS_SECURITIES_ACCOUNT_VIEW;
		}
	}

	/**
	 * 選定證券商中的所有分公司 - AJAX
	 * 
	 * @param sb_sh_id Integer --> 證券商流水號
	 * @return SecuritiesBrokerBranchEntity JSON
	 */
	@RequestMapping(value = "/settings/securities-account/securities-broker-branch-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public @ResponseBody String settingsSecuritiesAccountSecuritiesBrokerBranchListAjax(Integer sb_sh_id) {

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
