/*
 * iStockage
 * File: MemberDaoImpl.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/2/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.MemberDao;
import com.istockage.model.entity.MemberEntity;

/**
 * member DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "memberDao")
public class MemberDaoImpl implements MemberDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 新增會員
	 * 
	 * @param memberEntity
	 *            MemberEntity
	 * @return MemberEntity
	 */
	@Override
	public MemberEntity insert(MemberEntity memberEntity) {

		hibernateTemplate.save(memberEntity);

		return memberEntity;
	}

}
