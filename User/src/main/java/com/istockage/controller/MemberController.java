/*
 * iStockage
 * File: MemberController.java
 * Author: 詹晟
 * Created: 2018/3/26
 * Modified: 2018/3/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import static com.istockage.common.constant.ModelAttributeConstant.USER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.istockage.common.constant.ControllerConstant;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.service.MemberService;

/**
 * member controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(USER)
public class MemberController implements ControllerConstant {

	/**
	 * 注入 MemberService
	 */
	@Autowired
	private MemberService memberService;

	/**
	 * 登入 - init
	 * 
	 * @return /WEB-INF/view/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in", method = RequestMethod.GET)
	public String signInView() {

		return MEMBER_SIGN_IN_VIEW;
	}

	/**
	 * 登入 - submit
	 * 
	 * @param me_email
	 *            String --> 會員信箱
	 * @param me_password
	 *            String --> 會員密碼(原碼)
	 * @param model
	 *            Model
	 * @return /WEB-INF/view/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-in.do", method = RequestMethod.POST)
	public String signInAction(@RequestParam String me_email, @RequestParam String me_password, Model model) {

		MemberEntity user = memberService.signIn(me_email, me_password);

		if (user == null) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_EMAIL, me_email);
			model.addAttribute(MEMBER_PASSWORD, me_password);

			return MEMBER_SIGN_IN_VIEW;
		} else {

			// 放入 Session
			model.addAttribute(USER, user);

			return REDIRECT + INDEX_VIEW;
		}
	}

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
	 * @return /WEB-INF/view/index.jsp
	 */
	@RequestMapping(value = "/member/sign-up.do", method = RequestMethod.POST)
	public String signUpAction(MemberEntity memberEntity) {

		memberService.signUp(memberEntity);

		return REDIRECT + INDEX_VIEW;
	}

}
