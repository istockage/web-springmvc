/*
 * iStockage
 * File: MemberController.java
 * Author: 詹晟
 * Created: 2018/3/26
 * Modified: 2018/2/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.istockage.common.constant.ControllerConstant;
import com.istockage.model.entity.MemberEntity;

/**
 * member controller
 * 
 * @author 詹晟
 */
@Controller
public class MemberController implements ControllerConstant {

	/**
	 * 註冊 - init
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/view/member/sign-up.jsp
	 */
	@RequestMapping(value = "/member/sign-up", method = RequestMethod.GET)
	public String signUpView(Model model) {

		// 新增 form-backing object
		model.addAttribute(MEMBER_ENTITY, new MemberEntity());

		return MEMBER_SIGN_UP_VIEW;
	}

	/**
	 * 註冊 - submit
	 * 
	 * @param memberEntity
	 *            MemberEntity --> form-backing object
	 */
	@RequestMapping(value = "/member/sign-up.do", method = RequestMethod.POST)
	public String signUpAction(MemberEntity memberEntity) {

		return "";
	}

}
