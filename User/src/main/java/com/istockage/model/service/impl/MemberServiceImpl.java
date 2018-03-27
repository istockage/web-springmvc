/*
 * iStockage
 * File: MemberServiceImpl.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/2/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 * 註冊
	 * 
	 * @param memberEntity
	 *            MemberEntity
	 * @return MemberEntity
	 */
	@Override
	@Transactional
	public MemberEntity signUp(MemberEntity memberEntity) {

		String ad_salt = PasswordUtil.getSalt();

		memberEntity.setMe_password(PasswordUtil.getHashedPassword(memberEntity.getMe_password(), ad_salt));
		memberEntity.setMe_salt(ad_salt);
		memberEntity.setMe_signup_time(new java.util.Date());
		memberEntity.setMe_signin_number(0);
		memberEntity.setMe_update_pwd_time(new java.util.Date());
		memberEntity.setMe_update_info_time(new java.util.Date());

		return memberDao.insert(memberEntity);
	}

}
