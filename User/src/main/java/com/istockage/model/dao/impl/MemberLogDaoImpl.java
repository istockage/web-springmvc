/*
 * iStockage
 * File: MemberLogDaoImpl.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/3/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.MemberLogDao;
import com.istockage.model.entity.MemberLogEntity;

/**
 * member_log DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "memberLogDao")
public class MemberLogDaoImpl implements MemberLogDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 新增會員日誌
	 * 
	 * @param memberLogEntity
	 *            MemberLogEntity
	 * @return MemberLogEntity
	 */
	@Override
	public MemberLogEntity insert(MemberLogEntity memberLogEntity) {

		hibernateTemplate.save(memberLogEntity);

		return memberLogEntity;
	}

}
