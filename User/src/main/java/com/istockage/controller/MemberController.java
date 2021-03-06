/*
 * iStockage
 * File: MemberController.java
 * Author: 詹晟
 * Created: 2018/3/26
 * Modified: 2018/9/13
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.istockage.common.mail.SendMail;
import com.istockage.common.util.PasswordUtil;
import com.istockage.exception.PageNotFoundException;
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
	 * 注入 SendMail
	 */
	@Autowired
	private SendMail sendMail;

	/**
	 * 登入 - init
	 * 
	 * @return /WEB-INF/view/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in", method = RequestMethod.GET)
	public String secureSignInView() {

		return SECURE_SIGN_IN_VIEW;
	}

	/**
	 * 登入 - submit
	 * 
	 * @param me_email String --> 會員信箱
	 * @param me_password String --> 會員密碼(原碼)
	 * @param model Model
	 * @return /WEB-INF/view/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-in.do", method = RequestMethod.POST)
	public String secureSignInAction(@RequestParam String me_email, @RequestParam String me_password, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (me_email == null || me_email.isEmpty()) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_EMAIL, me_email);
			model.addAttribute(MEMBER_PASSWORD, me_password);
			model.addAttribute(ERROR, MSG_MEMBER_EMAIL_REQUIRE);

			logger.error("(" + className + "." + methodName + ") 登入失敗 (信箱未填)");

			return SECURE_SIGN_IN_VIEW;

		} else if (me_password == null || me_password.isEmpty()) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_EMAIL, me_email);
			model.addAttribute(MEMBER_PASSWORD, me_password);
			model.addAttribute(ERROR, MSG_MEMBER_PASSWORD_REQUIRE);

			logger.error("(" + className + "." + methodName + ") 登入失敗 (密碼未填)");

			return SECURE_SIGN_IN_VIEW;

		} else {

			MemberEntity user = memberService.signIn(me_email, me_password);

			if (user == null) {

				// 取得參數，並回填表單
				model.addAttribute(MEMBER_EMAIL, me_email);
				model.addAttribute(MEMBER_PASSWORD, me_password);
				model.addAttribute(ERROR, MSG_MEMBER_EMAIL_OR_PASSWORD_MISTAKE);

				logger.error("(" + className + "." + methodName + ") 登入失敗 (信箱或密碼錯誤)");

				return SECURE_SIGN_IN_VIEW;

			} else {

				if (user.getMe_activity_code() == MEMBER_ACTIVITY_CLOSE) {

					// 取得參數，並回填表單
					model.addAttribute(MEMBER_EMAIL, me_email);
					model.addAttribute(MEMBER_PASSWORD, me_password);
					model.addAttribute(ERROR, MSG_MEMBER_NOT_ACTIVITY);

					sendMail.signUpActivityMail(user);

					logger.error("(" + className + "." + methodName + ") 登入失敗 (帳號未啟用)");

					return SECURE_SIGN_IN_VIEW;
				}

				model.addAttribute(USER, user);

				request.setAttribute(MEMBER_LOG_KEY, OK);

				HttpSession session = request.getSession();
				String next = (String) session.getAttribute(NEXT);

				if (next != null) { // 經過 NotSignInInterceptor

					session.removeAttribute(NEXT);

					logger.info("(" + className + "." + methodName + ") 登入成功 (使用者: " + user.getMe_no() + ", 導向原請求頁面: "
							+ next + ")");

					return REDIRECT.concat(next);

				} else { // 未經過 NotSignInInterceptor

					logger.info("(" + className + "." + methodName + ") 登入成功 (使用者: " + user.getMe_no() + ", 導向首頁: "
							+ INDEX_VIEW + ")");

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
	public String secureForgetPasswordView() {

		return SECURE_FORGET_PASSWORD_VIEW;
	}

	/**
	 * 忘記密碼 - submit
	 * 
	 * @param me_email String --> 會員信箱
	 * @param model Model
	 * @return /WEB-INF/view/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password.do", method = RequestMethod.POST)
	public String secureForgetPasswordAction(@RequestParam String me_email, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (me_email == null || me_email.isEmpty()) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_EMAIL, me_email);
			model.addAttribute(ERROR, MSG_MEMBER_EMAIL_REQUIRE);

			logger.error("(" + className + "." + methodName + ") 發送失敗 (信箱未填)");

			return SECURE_FORGET_PASSWORD_VIEW;

		} else {

			MemberEntity memberEntity = memberService.selectByMe_email(me_email, null);

			if (memberEntity == null) {

				// 取得參數，並回填表單
				model.addAttribute(MEMBER_EMAIL, me_email);
				model.addAttribute(ERROR, MSG_MEMBER_EMAIL_MISTAKE);

				logger.error("(" + className + "." + methodName + ") 發送失敗 (信箱錯誤)");

				return SECURE_FORGET_PASSWORD_VIEW;

			} else {

				memberService.updateMe_random(memberEntity);

				model.addAttribute(SUCCESS, MSG_MEMBER_SEND_MAIL_SUCCESS);

				request.getSession().setAttribute(SESSION_MEMBER_EMAIL, me_email);
				request.setAttribute(MEMBER_ENTITY, memberEntity);
				request.setAttribute(MEMBER_LOG_KEY, OK);

				logger.info("(" + className + "." + methodName + ") 發送成功 (傳送至: " + me_email + ")");

				return SECURE_RESET_PASSWORD_VIEW;
			}
		}
	}

	/**
	 * 重設密碼 - init
	 * 
	 * @return /WEB-INF/view/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/reset-password", method = RequestMethod.GET)
	public String secureResetPasswordView() {

		return SECURE_RESET_PASSWORD_VIEW;
	}

	/**
	 * 重設密碼 - submit
	 * 
	 * @param me_random String --> 驗證碼(原碼)
	 * @param me_password_new String --> 新密碼(原碼)
	 * @param me_password_new_again String --> 重複新密碼(原碼)
	 * @param sessionStatus SessionStatus
	 * @param model Model
	 * @return /WEB-INF/view/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/reset-password.do", method = RequestMethod.POST)
	public String secureResetPasswordAction(@RequestParam String me_random, @RequestParam String me_password_new,
			@RequestParam String me_password_new_again, SessionStatus sessionStatus, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		HttpSession session = request.getSession();
		String me_email = (String) session.getAttribute(SESSION_MEMBER_EMAIL);

		if (me_email == null) {

			model.addAttribute(ERROR, MSG_MEMBER_RESET_PASSWORD_TIMEOUT);

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗 (操作逾時)");

			return SECURE_FORGET_PASSWORD_VIEW;
		}

		MemberEntity memberEntity = memberService.selectByMe_email(me_email, null);

		if (me_random == null || me_random.isEmpty() || me_password_new == null || me_password_new.isEmpty()
				|| me_password_new_again == null || me_password_new_again.isEmpty()) {

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗 (資料未填)");

			return SECURE_RESET_PASSWORD_VIEW;

		} else if (!me_password_new.matches("^[\\S]{8,32}$")) {

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗 (密碼格式錯誤)");

			return SECURE_RESET_PASSWORD_VIEW;

		} else if (!me_password_new.equals(me_password_new_again)) {

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗 (新密碼重複錯誤)");

			return SECURE_RESET_PASSWORD_VIEW;

		} else if (!memberEntity.getMe_random().equals(me_random)) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_RANDOM, me_random);
			model.addAttribute(MEMBER_PASSWORD_NEW, me_password_new);
			model.addAttribute(MEMBER_PASSWORD_NEW_AGAIN, me_password_new_again);
			model.addAttribute(ERROR, MSG_MEMBER_RANDOM_MISTAKE);

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗 (驗證碼錯誤)");

			return SECURE_RESET_PASSWORD_VIEW;

		} else {

			memberService.updateMe_password(memberEntity, me_password_new);

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			// 清除 HttpSession
			session.invalidate();

			model.addAttribute(SUCCESS, MSG_MEMBER_RESET_PASSWORD_SUCCESS);

			request.setAttribute(MEMBER_ENTITY, memberEntity);
			request.setAttribute(MEMBER_LOG_KEY, OK);

			logger.info("(" + className + "." + methodName + ") 密碼重設成功");

			return SECURE_SIGN_IN_VIEW;
		}
	}

	/**
	 * 登出 - submit
	 * 
	 * @param sessionStatus SessionStatus
	 * @return /WEB-INF/view/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-out.do", method = RequestMethod.GET)
	public String secureSignOutAction(SessionStatus sessionStatus) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		// 清除 @SessionAttributes
		sessionStatus.setComplete();

		// 清除 HttpSession
		request.getSession().invalidate();

		request.setAttribute(MEMBER_LOG_KEY, OK);

		logger.info("(" + className + "." + methodName + ") 登出成功");

		return REDIRECT + INDEX_VIEW;
	}

	/**
	 * 註冊 - init
	 * 
	 * @param model Model
	 * @return /WEB-INF/view/secure/sign-up.jsp
	 */
	@RequestMapping(value = "/secure/sign-up", method = RequestMethod.GET)
	public String secureSignUpView(Model model) {

		// 新增 form-backing object
		model.addAttribute(MEMBER_ENTITY, new MemberEntity());

		return SECURE_SIGN_UP_VIEW;
	}

	/**
	 * 註冊 - submit
	 * 
	 * @param me_password_again String --> 重複密碼(原碼)
	 * @param memberEntity MemberEntity --> form-backing object
	 * @param bindingResult BindingResult
	 * @return /WEB-INF/view/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-up.do", method = RequestMethod.POST)
	public String secureSignUpAction(@RequestParam String me_password_again, @Valid MemberEntity memberEntity,
			BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") 註冊失敗 (格式錯誤)");

			return SECURE_SIGN_UP_VIEW;

		} else if (!memberEntity.getMe_password().equals(me_password_again)) {

			logger.error("(" + className + "." + methodName + ") 註冊失敗 (密碼重複錯誤)");

			return SECURE_SIGN_UP_VIEW;

		} else if (memberService.selectByMe_email(memberEntity.getMe_email(), null) != null) {

			logger.error("(" + className + "." + methodName + ") 註冊失敗 (信箱重複)");

			return SECURE_SIGN_UP_VIEW;

		} else {

			memberService.signUp(memberEntity);

			request.getSession().setAttribute(SESSION_MEMBER_NO, memberEntity.getMe_no());
			request.setAttribute(MEMBER_ENTITY, memberEntity);
			request.setAttribute(MEMBER_LOG_KEY, OK);

			logger.info("(" + className + "." + methodName + ") 註冊成功 (會員編號: " + memberEntity.getMe_no() + ")");

			return REDIRECT + SECURE_SIGN_UP_MAIL_VIEW;
		}
	}

	/**
	 * 發送確認信 - init
	 * 
	 * @return /WEB-INF/view/secure/sign-up-mail.jsp
	 */
	@RequestMapping(value = "/secure/sign-up-mail", method = RequestMethod.GET)
	public String secureSignUpMailView() {

		return SECURE_SIGN_UP_MAIL_VIEW;
	}

	/**
	 * 發送確認信 - submit
	 * 
	 * @param model Model
	 * @return /WEB-INF/view/secure/sign-up-mail.jsp
	 */
	@RequestMapping(value = "/secure/sign-up-mail.do", method = RequestMethod.POST)
	public String secureSignUpMailAction(Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		String me_no = (String) request.getSession().getAttribute(SESSION_MEMBER_NO);

		if (me_no == null) {

			model.addAttribute(ERROR, MSG_MEMBER_SIGN_UP_MAIL_TIMEOUT);

			logger.error("(" + className + "." + methodName + ") 發送失敗 (操作逾時)");

			return SECURE_SIGN_UP_MAIL_AGAIN_VIEW;
		}

		MemberEntity memberEntity = memberService.selectByMe_no(me_no, MEMBER_ACTIVITY_CLOSE);

		sendMail.signUpActivityMail(memberEntity);

		model.addAttribute(SUCCESS, MSG_MEMBER_SEND_MAIL_SUCCESS);

		request.setAttribute(MEMBER_ENTITY, memberEntity);
		request.setAttribute(MEMBER_LOG_KEY, OK);

		logger.info("(" + className + "." + methodName + ") 發送成功 (會員編號: " + me_no + ")");

		return SECURE_SIGN_UP_MAIL_VIEW;
	}

	/**
	 * 重新發送確認信 - init
	 * 
	 * @return /WEB-INF/view/secure/sign-up-mail-again.jsp
	 */
	@RequestMapping(value = "/secure/sign-up-mail-again", method = RequestMethod.GET)
	public String secureSignUpMailAgainView() {

		return SECURE_SIGN_UP_MAIL_AGAIN_VIEW;
	}

	/**
	 * 重新發送確認信 - submit
	 * 
	 * @param me_email String --> 會員信箱
	 * @param model Model
	 * @return /WEB-INF/view/secure/sign-up-mail-again.jsp
	 */
	@RequestMapping(value = "/secure/sign-up-mail-again.do", method = RequestMethod.POST)
	public String secureSignUpMailAgainAction(@RequestParam String me_email, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		MemberEntity memberEntity = memberService.selectByMe_email(me_email, MEMBER_ACTIVITY_CLOSE);

		if (me_email == null || me_email.isEmpty()) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_EMAIL, me_email);
			model.addAttribute(ERROR, MSG_MEMBER_EMAIL_REQUIRE);

			logger.error("(" + className + "." + methodName + ") 發送失敗 (信箱未填)");

			return SECURE_SIGN_UP_MAIL_AGAIN_VIEW;

		} else if (memberEntity == null) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_EMAIL, me_email);
			model.addAttribute(ERROR, MSG_MEMBER_EMAIL_MISTAKE);

			logger.error("(" + className + "." + methodName + ") 發送失敗 (信箱錯誤)");

			return SECURE_SIGN_UP_MAIL_AGAIN_VIEW;

		} else {

			sendMail.signUpActivityMail(memberEntity);

			model.addAttribute(SUCCESS, MSG_MEMBER_SEND_MAIL_SUCCESS);

			request.setAttribute(MEMBER_ENTITY, memberEntity);
			request.setAttribute(MEMBER_LOG_KEY, OK);

			logger.info("(" + className + "." + methodName + ") 發送成功 (會員編號: " + memberEntity.getMe_no() + ")");

			return SECURE_SIGN_UP_MAIL_AGAIN_VIEW;
		}
	}

	/**
	 * 啟用帳號 - submit
	 * 
	 * @param me_no String --> 會員編號
	 * @return /WEB-INF/view/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-up-activity.do", method = RequestMethod.GET)
	public String secureSignUpActivityAction(@RequestParam String me_no) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		MemberEntity memberEntity;
		try {
			memberEntity = memberService.updateMe_activity_code(me_no);

		} catch (PageNotFoundException e) {

			logger.error("(" + className + "." + methodName + ") 啟用帳號失敗 (會員編號錯誤: " + me_no + ")");

			return ERROR_PAGE_NOT_FOUND_VIEW;
		}

		request.setAttribute(MEMBER_ENTITY, memberEntity);
		request.setAttribute(MEMBER_LOG_KEY, OK);

		logger.info("(" + className + "." + methodName + ") 啟用帳號成功 (會員編號: " + me_no + ")");

		return REDIRECT + SECURE_SIGN_IN_VIEW;
	}

	/**
	 * 信箱重複驗證 (sign-up) - AJAX
	 * 
	 * @param me_email String --> 會員信箱
	 * @return String
	 */
	@RequestMapping(value = "/secure/sign-up-email-repeat.ajax", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String secureSignUpEmailRepeatAjax(String me_email) {

		MemberEntity memberEntity = memberService.selectByMe_email(me_email, null);

		return (memberEntity != null) ? MSG_MEMBER_EMAIL_REPEAT : TRUE;
	}

	/**
	 * 個人帳戶 - init
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param model Model
	 * @return /WEB-INF/view/settings/account.jsp
	 */
	@RequestMapping(value = "/settings/account", method = RequestMethod.GET)
	public String settingsAccountView(@SessionAttribute(USER) MemberEntity user, Model model) {

		model.addAttribute(USER, memberService.selectByMe_id(user.getMe_id()));

		return SETTINGS_ACCOUNT_VIEW;
	}

	/**
	 * 個人帳戶(基本資料) - submit
	 * 
	 * @param user MemberEntity --> form-backing object
	 * @param bindingResult BindingResult
	 * @return /WEB-INF/view/settings/account.jsp
	 */
	@RequestMapping(value = "/settings/account/info.do", method = RequestMethod.POST)
	public String settingsAccountInfoAction(@Valid @ModelAttribute(USER) MemberEntity user,
			BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") 基本資料修改失敗 (格式錯誤)");

			return SETTINGS_ACCOUNT_VIEW;

		} else {

			memberService.update(user);

			request.setAttribute(MEMBER_LOG_KEY, OK);

			logger.info("(" + className + "." + methodName + ") 基本資料修改成功");

			return REDIRECT + SETTINGS_ACCOUNT_VIEW;
		}
	}

	/**
	 * 個人帳戶(變更密碼) - submit
	 * 
	 * @param user MemberEntity --> SessionAttribute
	 * @param me_password String --> 舊密碼(原碼)
	 * @param me_password_new String --> 新密碼(原碼)
	 * @param me_password_new_again String --> 重複新密碼(原碼)
	 * @param model Model
	 * @return /WEB-INF/view/settings/account.jsp
	 */
	@RequestMapping(value = "/settings/account/change-password.do", method = RequestMethod.POST)
	public String settingsAccountChangePasswordAction(@SessionAttribute(USER) MemberEntity user,
			@RequestParam String me_password, @RequestParam String me_password_new,
			@RequestParam String me_password_new_again, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (me_password == null || me_password.isEmpty() || me_password_new == null || me_password_new.isEmpty()
				|| me_password_new_again == null || me_password_new_again.isEmpty()) {

			logger.error("(" + className + "." + methodName + ") 密碼變更失敗 (資料未填)");

			return SETTINGS_ACCOUNT_VIEW;

		} else if (!me_password_new.matches("^[\\S]{8,32}$")) {

			logger.error("(" + className + "." + methodName + ") 密碼變更失敗 (密碼格式錯誤)");

			return SETTINGS_ACCOUNT_VIEW;

		} else if (!me_password_new.equals(me_password_new_again)) {

			logger.error("(" + className + "." + methodName + ") 密碼變更失敗 (新密碼重複錯誤)");

			return SETTINGS_ACCOUNT_VIEW;

		} else if (!PasswordUtil.getHashedPassword(me_password, user.getMe_salt())
				.equals(memberService.selectByMe_id(user.getMe_id()).getMe_password())) {

			// 取得參數，並回填表單
			model.addAttribute(MEMBER_PASSWORD, me_password);
			model.addAttribute(MEMBER_PASSWORD_NEW, me_password_new);
			model.addAttribute(MEMBER_PASSWORD_NEW_AGAIN, me_password_new_again);
			model.addAttribute(ERROR, MSG_MEMBER_PASSWORD_MISTAKE);

			logger.error("(" + className + "." + methodName + ") 密碼變更失敗 (密碼錯誤)");

			return SETTINGS_ACCOUNT_VIEW;

		} else {

			memberService.updateMe_password(user, me_password_new);

			model.addAttribute(SUCCESS, MSG_MEMBER_CHANGE_PASSWORD_SUCCESS);

			request.setAttribute(MEMBER_LOG_KEY, OK);

			logger.info("(" + className + "." + methodName + ") 密碼變更成功");

			return SETTINGS_ACCOUNT_VIEW;
		}
	}

	/**
	 * 帳戶 - init
	 * 
	 * @return /WEB-INF/view/member/profile.jsp
	 */
	@RequestMapping(value = "/member/profile", method = RequestMethod.GET)
	public String memberProfileView() {

		return MEMBER_PROFILE_VIEW;
	}

}
