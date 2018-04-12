/*
 * iStockage
 * File: MemberServiceImpl.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/4/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istockage.common.mail.SendMail;
import com.istockage.common.util.MathUtil;
import com.istockage.common.util.PasswordUtil;
import com.istockage.exception.PageNotFoundException;
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

		MemberEntity memberEntity = memberDao.selectByMe_email(me_email, MEMBER_ACTIVITY_OPEN);

		if (memberEntity == null) {

			// 信箱錯誤或未啟用
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

		memberEntity.setMe_no(MathUtil.getMe_no());
		memberEntity.setMe_password(PasswordUtil.getHashedPassword(memberEntity.getMe_password(), me_salt));
		memberEntity.setMe_salt(me_salt);
		memberEntity.setMe_activity_code(MEMBER_ACTIVITY_CLOSE);
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
	 * @param me_activity_code
	 *            Byte --> 啟用狀態
	 * @return null / MemberEntity
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberEntity selectByMe_email(String me_email, Byte me_activity_code) {

		return memberDao.selectByMe_email(me_email, me_activity_code);
	}

	/**
	 * 啟用帳號
	 * 
	 * @param me_no
	 *            String --> 會員編號
	 * @throws PageNotFoundException
	 * @return MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity updateMe_activity_code(String me_no) throws PageNotFoundException {

		MemberEntity memberEntity = memberDao.selectByMe_no(me_no, MEMBER_ACTIVITY_CLOSE);

		if (memberEntity == null) {

			throw new PageNotFoundException();
		}

		memberEntity.setMe_activity_code(MEMBER_ACTIVITY_OPEN);

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

			sendMail.forgetPasswordMail(memberEntity, MathUtil.getMe_password_random());
		}

		memberEntity.setMe_password(PasswordUtil.getHashedPassword(me_password_new, memberEntity.getMe_salt()));

		return memberDao.update(memberEntity);
	}

}
