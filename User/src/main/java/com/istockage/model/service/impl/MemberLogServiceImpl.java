/*
 * iStockage
 * File: MemberLogServiceImpl.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/3/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istockage.model.dao.MemberLogDao;
import com.istockage.model.entity.MemberLogEntity;
import com.istockage.model.service.MemberLogService;

/**
 * member_log service implement
 * 
 * @author 詹晟
 */
@Service(value = "memberLogService")
public class MemberLogServiceImpl implements MemberLogService {

	/**
	 * 注入 MemberLogDao
	 */
	@Autowired
	private MemberLogDao memberLogDao;

	/**
	 * 新增會員日誌
	 * 
	 * @param memberLogEntity
	 *            MemberLogEntity
	 * @return MemberLogEntity
	 */
	@Override
	@Transactional
	public MemberLogEntity insert(MemberLogEntity memberLogEntity) {

		return memberLogDao.insert(memberLogEntity);
	}

}
