package com.istockage.common.message;

public interface ErrorMessage {

	// member/sign-up
	public static final String MSG_MEMBER_EMAIL_REPEAT = "這個信箱已經有人使用了";

	// secure/sign-in
	public static final String MSG_MEMBER_EMAIL_REQUIRE = "請填入信箱。";

	public static final String MSG_MEMBER_PASSWORD_REQUIRE = "請填入密碼。";

	public static final String MSG_MEMBER_EMAIL_OR_PASSWORD_MISTAKE = "信箱或密碼錯誤。";

}
