package com.istockage.common.message;

public interface ErrorMessage {

	// secure/sign-in
	// secure/forget-password
	// secure/sign-up-mail-again
	public static final String MSG_MEMBER_EMAIL_REQUIRE = "請填入信箱。";

	public static final String MSG_MEMBER_PASSWORD_REQUIRE = "請填入密碼。";

	public static final String MSG_MEMBER_EMAIL_OR_PASSWORD_MISTAKE = "信箱或密碼錯誤。";

	public static final String MSG_MEMBER_NOT_ACTIVITY = "帳號未啟用，確認信已發送至您的信箱，請開啟信件並點擊連結以啟用您的帳號。";

	// secure/forget-password
	// secure/sign-up-mail-again
	public static final String MSG_MEMBER_EMAIL_MISTAKE = "沒有這個信箱。";

	// secure/reset-password
	public static final String MSG_MEMBER_RESET_PASSWORD_TIMEOUT = "操作逾時，請重新輸入您的信箱。";

	public static final String MSG_MEMBER_RANDOM_MISTAKE = "驗證碼錯誤。";

	// secure/sign-up
	public static final String MSG_MEMBER_EMAIL_REPEAT = "這個信箱已經有人使用了";

	// secure/sign-up-mail
	public static final String MSG_MEMBER_SIGN_UP_MAIL_TIMEOUT = "操作逾時，請重新輸入您的信箱。";

	// settings/account
	public static final String MSG_MEMBER_PASSWORD_MISTAKE = "密碼錯誤。";

}
