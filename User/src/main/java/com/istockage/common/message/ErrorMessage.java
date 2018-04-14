package com.istockage.common.message;

public interface ErrorMessage {

	// secure/sign-in
	// secure/sign-up-mail-again
	public static final String MSG_MEMBER_EMAIL_REQUIRE = "請填入信箱。";

	public static final String MSG_MEMBER_PASSWORD_REQUIRE = "請填入密碼。";

	public static final String MSG_MEMBER_EMAIL_OR_PASSWORD_MISTAKE = "信箱或密碼錯誤。";

	// secure/forget-password
	// secure/sign-up-mail-again
	public static final String MSG_MEMBER_EMAIL_MISTAKE = "沒有這個信箱。";

	// secure/reset-password
	public static final String MSG_MEMBER_RANDOM_MISTAKE = "驗證碼錯誤。";

	// secure/sign-up
	public static final String MSG_MEMBER_EMAIL_REPEAT = "這個信箱已經有人使用了";

}
