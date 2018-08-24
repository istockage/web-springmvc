/*
 * iStockage
 * File: SecuritiesBrokerHeadDaoImpl.java
 * Author: 詹晟
 * Created: 2018/8/25
 * Modified: 2018/8/25
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.SecuritiesBrokerHeadDao;
import com.istockage.model.entity.SecuritiesBrokerHeadEntity;

/**
 * securities_broker_head DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "securitiesBrokerHeadDao")
public class SecuritiesBrokerHeadDaoImpl implements SecuritiesBrokerHeadDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋所有證券商
	 * 
	 * @return List<SecuritiesBrokerHeadEntity>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SecuritiesBrokerHeadEntity> selectByAll() {

		return (List<SecuritiesBrokerHeadEntity>) hibernateTemplate.find(HQL_SELECT_SECURITIES_BROKER_HEAD);
	}

}
