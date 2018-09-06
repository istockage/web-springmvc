/*
 * iStockage
 * File: StockServiceImpl.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istockage.model.dao.StockDao;
import com.istockage.model.entity.StockEntity;
import com.istockage.model.service.StockService;

/**
 * stock service implement
 * 
 * @author 詹晟
 */
@Service(value = "stockService")
public class StockServiceImpl implements StockService {

	/**
	 * 注入 StockDao
	 */
	@Autowired
	private StockDao stockDao;

	/**
	 * 新增股票交易
	 * 
	 * @param stockEntity StockEntity
	 * @return StockEntity
	 */
	@Override
	@Transactional
	public StockEntity insert(StockEntity stockEntity) {

		return stockDao.insert(stockEntity);
	}

}
