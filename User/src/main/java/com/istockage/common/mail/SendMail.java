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
	 * 寄送 Email
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
	 * 忘記密碼 Email
	 * 
	 * @param memberEntity
	 *            MemberEntity
	 * @param me_password_random
	 *            String --> 驗證碼
	 */
	public void forgetPasswordMail(MemberEntity memberEntity, String me_password_random) {

		String to = memberEntity.getMe_email();
		String from = FORGET_PASSWORD_MAIL_FROM;
		String subject = FORGET_PASSWORD_MAIL_SUBJECT;
		String text = "您的驗證碼為: " + me_password_random + "。";

		sendMail(to, from, subject, text);
	}

}
