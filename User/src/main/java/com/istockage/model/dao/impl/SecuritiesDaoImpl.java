/*
 * iStockage
 * File: SecuritiesDaoImpl.java
 * Author: 詹晟
 * Created: 2018/9/20
 * Modified: 2018/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.SecuritiesDao;
import com.istockage.model.entity.SecuritiesEntity;

/**
 * securities DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "securitiesDao")
public class SecuritiesDaoImpl implements SecuritiesDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 股票代號搜尋
	 * 
	 * @param se_no String --> 股票代號
	 * @return SecuritiesEntity
	 */
	@Override
	public SecuritiesEntity selectBySe_no(String se_no) {

		return hibernateTemplate.get(SecuritiesEntity.class, se_no);
	}

}
