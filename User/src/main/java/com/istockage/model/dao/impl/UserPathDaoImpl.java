/*
 * iStockage
 * File: UserPathDaoImpl.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/9/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.UserPathDao;
import com.istockage.model.entity.UserPathEntity;

/**
 * user_path DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "userPathDao")
public class UserPathDaoImpl implements UserPathDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * path 搜尋
	 * 
	 * @param up_path String --> path
	 * @return UserPathEntity
	 */
	@Override
	public UserPathEntity selectByUp_path(String up_path) {

		return hibernateTemplate.get(UserPathEntity.class, up_path);
	}

}
