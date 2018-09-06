/*
 * iStockage
 * File: StockDaoImpl.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.StockDao;
import com.istockage.model.entity.StockEntity;

/**
 * stock DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "stockDao")
public class StockDaoImpl implements StockDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 新增股票交易
	 * 
	 * @param stockEntity StockEntity
	 * @return StockEntity
	 */
	@Override
	public StockEntity insert(StockEntity stockEntity) {

		hibernateTemplate.save(stockEntity);

		return stockEntity;
	}

}
