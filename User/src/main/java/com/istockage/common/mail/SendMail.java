package com.istockage.common.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.istockage.model.entity.MemberEntity;

@Component(value = "sendMail")
public class SendMail implements MailContent {

	/**
	 * 注入 MailSender
	 */
	@Autowired
	private MailSender mailSender;

	/**
	 * 寄送 Mail
	 * 
	 * @param to
	 *            String --> 收件者
	 * @param from
	 *            String --> 寄件者
	 * @param subject
	 *            String --> 信件主旨
	 * @param text
	 *            String --> 信件內容
	 */
	private void sendMail(String to, String from, String subject, String text) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setTo(to);
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);

		mailSender.send(simpleMailMessage);
	}

	/**
	 * 啟用帳號 Mail
	 * 
	 * @param memberEntity
	 *            MemberEntity
	 */
	public void signUpActivityMail(MemberEntity memberEntity) {

		String to = memberEntity.getMe_email();
		String from = MAIL_FROM;
		String subject = MAIL_SUBJECT_SIGN_UP_ACTIVITY;
		String text = "http://localhost:8080/User/secure/sign-up-activity.do?me_no=" + memberEntity.getMe_no();

		sendMail(to, from, subject, text);
	}

	/**
	 * 忘記密碼 Mail
	 * 
	 * @param memberEntity
	 *            MemberEntity
	 * @param me_random
	 *            String --> 驗證碼
	 */
	public void forgetPasswordMail(MemberEntity memberEntity, String me_random) {

		String to = memberEntity.getMe_email();
		String from = MAIL_FROM;
		String subject = MAIL_SUBJECT_FORGET_PASSWORD;
		String text = "您的驗證碼為: " + me_random + "。";

		sendMail(to, from, subject, text);
	}

}
