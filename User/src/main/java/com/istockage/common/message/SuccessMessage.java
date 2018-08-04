package com.istockage.common.message;

public interface SuccessMessage {

	// secure/forget-password
	// secure/sign-up-mail
	// secure/sign-up-mail-again
	public static final String MSG_MEMBER_SEND_MAIL_SUCCESS = "發送成功。";

	// secure/reset-password
	public static final String MSG_MEMBER_RESET_PASSWORD_SUCCESS = "密碼重設成功，請以新密碼登入。";

	// settings/account
	public static final String MSG_MEMBER_CHANGE_PASSWORD_SUCCESS = "密碼變更成功，下次請以新密碼登入。";

}
