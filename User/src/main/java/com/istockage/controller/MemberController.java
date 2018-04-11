/*
 * iStockage
 * File: MemberController.java
 * Author: 詹晟
 * Created: 2018/3/26
 * Modified: 2018/4/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.controller;

import static com.istockage.common.constant.ModelAttributeConstant.USER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.istockage.common.constant.ControllerConstant;
import com.istockage.common.message.ErrorMessage;
import com.istockage.common.util.PasswordUtil;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.service.MemberService;

/**
 * member controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(USER)
public class MemberController implements ControllerConstant, ErrorMessage {

	private static final Logger logger = Logger.getLogger(MemberController.class);

	private String className = this.getClass().getSimpleName();

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

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

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (me_email == null || me_email.isEmpty()) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_EMAIL, me_email);
			model.addAttribute(MEMBER_PASSWORD, me_password);
			model.addAttribute(ERROR, MSG_MEMBER_EMAIL_REQUIRE);

			logger.error("(" + className + "." + methodName + ") 登入失敗: 帳號未填");

			return MEMBER_SIGN_IN_VIEW;

		} else if (me_password == null || me_password.isEmpty()) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_EMAIL, me_email);
			model.addAttribute(MEMBER_PASSWORD, me_password);
			model.addAttribute(ERROR, MSG_MEMBER_PASSWORD_REQUIRE);

			logger.error("(" + className + "." + methodName + ") 登入失敗: 密碼未填");

			return MEMBER_SIGN_IN_VIEW;

		} else {

			MemberEntity user = memberService.signIn(me_email, me_password);

			if (user == null) {

				// 取得參數，並回填表單
				model.addAttribute(MEMBER_EMAIL, me_email);
				model.addAttribute(MEMBER_PASSWORD, me_password);
				model.addAttribute(ERROR, MSG_MEMBER_EMAIL_OR_PASSWORD_MISTAKE);

				logger.error("(" + className + "." + methodName + ") 登入失敗: 帳號或密碼錯誤");

				return MEMBER_SIGN_IN_VIEW;

			} else {

				model.addAttribute(USER, user);

				request.setAttribute(MEMBER_LOG_KEY, OK);

				HttpSession session = request.getSession();
				String next = (String) session.getAttribute(NEXT);

				if (next != null) { // 經過 NoSignInInterceptor

					session.removeAttribute(NEXT);

					logger.info("(" + className + "." + methodName + ") 登入成功，使用者: " + user.getMe_email() + "，導向原請求頁面: "
							+ next);

					return REDIRECT.concat(next);

				} else { // 未經過 NoSignInInterceptor

					logger.info("(" + className + "." + methodName + ") 登入成功，使用者: " + user.getMe_email() + "，導向首頁: "
							+ INDEX_VIEW);

					return REDIRECT + INDEX_VIEW;
				}
			}
		}
	}

	/**
	 * 忘記密碼 - init
	 * 
	 * @return /WEB-INF/view/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password", method = RequestMethod.GET)
	public String forgetPasswordView() {

		return MEMBER_FORGET_PASSWORD_VIEW;
	}

	/**
	 * 忘記密碼 - submit
	 * 
	 * @param me_email
	 *            String --> 會員信箱
	 * @param model
	 *            Model
	 * @return /WEB-INF/view/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordAction(@RequestParam String me_email, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (me_email == null || me_email.isEmpty()) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_EMAIL, me_email);
			model.addAttribute(ERROR, MSG_MEMBER_EMAIL_REQUIRE);

			logger.error("(" + className + "." + methodName + ") 發送失敗: 信箱未填");

			return MEMBER_FORGET_PASSWORD_VIEW;

		} else {

			MemberEntity memberEntity = memberService.selectByMe_email(me_email, MEMBER_ACTIVITY_OPEN);

			if (memberEntity == null) {

				// 取得參數，並回填表單
				model.addAttribute(MEMBER_EMAIL, me_email);
				model.addAttribute(ERROR, MSG_MEMBER_EMAIL_MISTAKE);

				logger.error("(" + className + "." + methodName + ") 發送失敗: 信箱錯誤");

				return MEMBER_FORGET_PASSWORD_VIEW;

			} else {

				memberService.updateMe_password(memberEntity, null);

				request.getSession().setAttribute(SESSION_MEMBER_EMAIL, me_email);
				request.setAttribute(MEMBER_LOG_KEY, OK);

				logger.info("(" + className + "." + methodName + ") 發送成功，傳送至: " + me_email);

				return REDIRECT + MEMBER_RESET_PASSWORD_VIEW;
			}
		}
	}

	/**
	 * 重設密碼 - init
	 * 
	 * @return /WEB-INF/view/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/reset-password", method = RequestMethod.GET)
	public String resetPasswordView() {

		return MEMBER_RESET_PASSWORD_VIEW;
	}

	/**
	 * 重設密碼 - submit
	 * 
	 * @param me_password_random
	 *            String --> 驗證碼(原碼)
	 * @param me_password_new
	 *            String --> 新密碼(原碼)
	 * @param me_password_new_again
	 *            String --> 重複新密碼(原碼)
	 * @param sessionStatus
	 *            SessionStatus
	 * @param model
	 *            Model
	 * @return /WEB-INF/view/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/reset-password.do", method = RequestMethod.POST)
	public String resetPasswordAction(@RequestParam String me_password_random, @RequestParam String me_password_new,
			@RequestParam String me_password_new_again, SessionStatus sessionStatus, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		HttpSession session = request.getSession();
		String me_email = (String) session.getAttribute(SESSION_MEMBER_EMAIL);

		MemberEntity memberEntity = memberService.selectByMe_email(me_email, MEMBER_ACTIVITY_OPEN);

		if (me_password_random == null || me_password_random.isEmpty() || me_password_new == null
				|| me_password_new.isEmpty() || me_password_new_again == null || me_password_new_again.isEmpty()) {

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗: 資料未填");

			return MEMBER_RESET_PASSWORD_VIEW;

		} else if (!me_password_new.matches("^[\\S]{8,32}$")) {

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗: 密碼格式錯誤");

			return MEMBER_RESET_PASSWORD_VIEW;

		} else if (!me_password_new.equals(me_password_new_again)) {

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗: 新密碼重複錯誤");

			return MEMBER_RESET_PASSWORD_VIEW;

		} else if (!memberEntity.getMe_password()
				.equals(PasswordUtil.getHashedPassword(me_password_random, memberEntity.getMe_salt()))) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_PASSWORD_RANDOM, me_password_random);
			model.addAttribute(MEMBER_PASSWORD_NEW, me_password_new);
			model.addAttribute(MEMBER_PASSWORD_NEW_AGAIN, me_password_new_again);
			model.addAttribute(ERROR, MSG_MEMBER_RANDOM_MISTAKE);

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗: 驗證碼錯誤");

			return MEMBER_RESET_PASSWORD_VIEW;

		} else {

			memberService.updateMe_password(memberEntity, me_password_new);

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			// 清除所有 HttpSession
			session.invalidate();

			request.setAttribute(MEMBER_LOG_KEY, OK);

			logger.info("(" + className + "." + methodName + ") 密碼重設成功");

			return REDIRECT + MEMBER_SIGN_IN_VIEW;
		}
	}

	/**
	 * 登出 - submit
	 * 
	 * @param sessionStatus
	 *            SessionStatus
	 * @return /WEB-INF/view/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-out.do", method = RequestMethod.GET)
	public String signOutAction(SessionStatus sessionStatus) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		// 清除 @SessionAttributes
		sessionStatus.setComplete();

		// 清除所有 HttpSession
		request.getSession().invalidate();

		request.setAttribute(MEMBER_LOG_KEY, OK);

		logger.info("(" + className + "." + methodName + ") 登出成功");

		return REDIRECT + INDEX_VIEW;
	}

	/**
	 * 註冊 - init
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/view/secure/sign-up.jsp
	 */
	@RequestMapping(value = "/secure/sign-up", method = RequestMethod.GET)
	public String signUpView(Model model) {

		// 新增 form-backing object
		model.addAttribute(MEMBER_ENTITY, new MemberEntity());

		return MEMBER_SIGN_UP_VIEW;
	}

	/**
	 * 註冊 - submit
	 * 
	 * @param me_password_again
	 *            String --> 重複密碼(原碼)
	 * @param memberEntity
	 *            MemberEntity --> form-backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/view/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-up.do", method = RequestMethod.POST)
	public String signUpAction(@RequestParam String me_password_again, @Valid MemberEntity memberEntity,
			BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") 註冊失敗: 格式錯誤");

			return MEMBER_SIGN_UP_VIEW;

		} else if (!memberEntity.getMe_password().equals(me_password_again)) {

			logger.error("(" + className + "." + methodName + ") 註冊失敗: 密碼重複錯誤");

			return MEMBER_SIGN_UP_VIEW;

		} else if (memberService.selectByMe_email(memberEntity.getMe_email(), null) != null) {

			logger.error("(" + className + "." + methodName + ") 註冊失敗: 帳號重複");

			return MEMBER_SIGN_UP_VIEW;

		} else {

			memberService.signUp(memberEntity);

			request.setAttribute(MEMBER_ENTITY, memberEntity);
			request.setAttribute(MEMBER_LOG_KEY, OK);

			logger.info("(" + className + "." + methodName + ") 註冊成功");

			return REDIRECT + INDEX_VIEW;
		}
	}

	/**
	 * 啟用帳號
	 * 
	 * @param me_email
	 *            String --> 會員信箱
	 * @return /WEB-INF/view/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-up-activity.do", method = RequestMethod.GET)
	public String signUpActivityAction(@RequestParam String me_email) {

		memberService.updateMe_activity(me_email);

		return REDIRECT + MEMBER_SIGN_IN_VIEW;
	}

	/**
	 * 信箱重複驗證 (sign-up) - AJAX
	 * 
	 * @param me_email
	 *            String --> 會員信箱
	 * @return String
	 */
	@RequestMapping(value = "/secure/sign-up-email-repeat.ajax", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String signUpEmailRepeatAjax(String me_email) {

		MemberEntity memberEntity = memberService.selectByMe_email(me_email, null);

		return (memberEntity != null) ? MSG_MEMBER_EMAIL_REPEAT : TRUE;
	}

}
