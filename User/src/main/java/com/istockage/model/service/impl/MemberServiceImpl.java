/*
 * iStockage
 * File: MemberServiceImpl.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/8/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import java.util.Date;

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
	 * @param me_email String --> 會員信箱
	 * @param me_password String --> 會員密碼(原碼)
	 * @return null / null / MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity signIn(String me_email, String me_password) {

		MemberEntity memberEntity = memberDao.selectByMe_email(me_email, null);

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
	 * @param memberEntity MemberEntity
	 * @return MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity signUp(MemberEntity memberEntity) {

		String me_no;
		do {
			me_no = MathUtil.getMe_no();
		} while (memberDao.selectByMe_no(me_no, null) != null);

		String me_salt = PasswordUtil.getSalt();

		memberEntity.setMe_no(me_no);
		memberEntity.setMe_password(PasswordUtil.getHashedPassword(memberEntity.getMe_password(), me_salt));
		memberEntity.setMe_salt(me_salt);
		memberEntity.setMe_activity_code(MEMBER_ACTIVITY_CLOSE);
		memberEntity.setMe_signup_time(new Date());
		memberEntity.setMe_update_pwd_time(new Date());
		memberEntity.setMe_update_info_time(new Date());

		sendMail.signUpActivityMail(memberEntity);

		return memberDao.insert(memberEntity);
	}

	/**
	 * 會員流水號搜尋
	 * 
	 * @param me_id Integer --> 會員流水號
	 * @return MemberEntity
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberEntity selectByMe_id(Integer me_id) {

		return memberDao.selectByMe_id(me_id);
	}

	/**
	 * 會員編號搜尋
	 * 
	 * @param me_no String --> 會員編號
	 * @param me_activity_code Byte --> 啟用狀態
	 * @return null / MemberEntity
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberEntity selectByMe_no(String me_no, Byte me_activity_code) {

		return memberDao.selectByMe_no(me_no, me_activity_code);
	}

	/**
	 * 會員信箱搜尋
	 * 
	 * @param me_email String --> 會員信箱
	 * @param me_activity_code Byte --> 啟用狀態
	 * @return null / MemberEntity
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberEntity selectByMe_email(String me_email, Byte me_activity_code) {

		return memberDao.selectByMe_email(me_email, me_activity_code);
	}

	/**
	 * 修改資料
	 * 
	 * @param memberEntity MemberEntity
	 * @return MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity update(MemberEntity memberEntity) {

		memberEntity.setMe_lastname(memberEntity.getMe_lastname().trim());
		memberEntity.setMe_firstname(memberEntity.getMe_firstname().trim());
		memberEntity.setMe_email(memberEntity.getMe_email().trim());
		memberEntity.setMe_update_info_time(new Date());

		return memberDao.update(memberEntity);
	}

	/**
	 * 修改密碼
	 * 
	 * @param memberEntity MemberEntity
	 * @param me_password_new String --> 新密碼(原碼)
	 * @return MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity updateMe_password(MemberEntity memberEntity, String me_password_new) {

		memberEntity.setMe_password(PasswordUtil.getHashedPassword(me_password_new, memberEntity.getMe_salt()));
		memberEntity.setMe_update_pwd_time(new Date());

		return memberDao.update(memberEntity);
	}

	/**
	 * 修改驗證碼
	 * 
	 * @param memberEntity MemberEntity
	 * @return MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity updateMe_random(MemberEntity memberEntity) {

		String me_random = MathUtil.getMe_random();

		memberEntity.setMe_random(me_random);

		sendMail.forgetPasswordMail(memberEntity, me_random);

		return memberDao.update(memberEntity);
	}

	/**
	 * 啟用帳號
	 * 
	 * @param me_no String --> 會員編號
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

}
