/*
 * iStockage
 * File: MemberServiceImpl.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/4/11
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istockage.common.mail.SendMail;
import com.istockage.common.util.PasswordUtil;
import com.istockage.model.dao.MemberDao;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.service.MemberService;

/**
 * member service implement
 * 
 * @author 詹晟
 */
@Service(value = "memberService")
public class MemberServiceImpl implements MemberService {

	/**
	 * 注入 MemberDao
	 */
	@Autowired
	private MemberDao memberDao;

	/**
	 * 注入 SendMail
	 */
	@Autowired
	private SendMail sendMail;

	/**
	 * 登入
	 * 
	 * @param me_email
	 *            String --> 會員信箱
	 * @param me_password
	 *            String --> 會員密碼(原碼)
	 * @return null / null / MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity signIn(String me_email, String me_password) {

		MemberEntity memberEntity = memberDao.selectByMe_email(me_email);

		if (memberEntity == null) {

			// 信箱錯誤
			return null;

		} else {

			String me_salt = memberEntity.getMe_salt();

			if (!PasswordUtil.getHashedPassword(me_password, me_salt).equals(memberEntity.getMe_password())) {

				// 密碼錯誤
				return null;

			} else {

				// 信箱及密碼正確
				return memberEntity;
			}
		}
	}

	/**
	 * 註冊
	 * 
	 * @param memberEntity
	 *            MemberEntity
	 * @return MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity signUp(MemberEntity memberEntity) {

		String me_salt = PasswordUtil.getSalt();

		memberEntity.setMe_password(PasswordUtil.getHashedPassword(memberEntity.getMe_password(), me_salt));
		memberEntity.setMe_salt(me_salt);
		memberEntity.setMe_activity((byte) 0);
		memberEntity.setMe_signup_time(new java.util.Date());
		memberEntity.setMe_update_pwd_time(new java.util.Date());
		memberEntity.setMe_update_info_time(new java.util.Date());

		sendMail.signUpActivityMail(memberEntity);

		return memberDao.insert(memberEntity);
	}

	/**
	 * 會員信箱搜尋
	 * 
	 * @param me_email
	 *            String --> 會員信箱
	 * @return null / MemberEntity
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberEntity selectByMe_email(String me_email) {

		return memberDao.selectByMe_email(me_email);
	}

	/**
	 * 啟用帳號
	 * 
	 * @param me_email
	 *            String --> 會員信箱
	 * @return MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity updateMe_activity(String me_email) {

		MemberEntity memberEntity = memberDao.selectByMe_email(me_email);

		memberEntity.setMe_activity((byte) 1);

		return memberDao.update(memberEntity);
	}

	/**
	 * 修改密碼
	 * 
	 * @param memberEntity
	 *            MemberEntity
	 * @param me_password_new
	 *            String --> 新密碼(原碼)
	 * @return MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity updateMe_password(MemberEntity memberEntity, String me_password_new) {

		if (me_password_new == null) {

			int random = (int) (Math.random() * 1000000);
			me_password_new = String.format("%06d", random);

			sendMail.forgetPasswordMail(memberEntity, me_password_new);
		}

		memberEntity.setMe_password(PasswordUtil.getHashedPassword(me_password_new, memberEntity.getMe_salt()));

		return memberDao.update(memberEntity);
	}

}
