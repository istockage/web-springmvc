/*
 * iStockage
 * File: StockServiceImpl.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istockage.model.dao.SecuritiesAccountDao;
import com.istockage.model.dao.StockDao;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.SecuritiesAccountEntity;
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
	 * 注入 SecuritiesAccountDao
	 */
	@Autowired
	private SecuritiesAccountDao securitiesAccountDao;

	/**
	 * 注入 StockDao
	 */
	@Autowired
	private StockDao stockDao;

	/**
	 * 搜尋所有股票交易明細 (分頁) / 搜尋所有股票庫存明細 (分頁)
	 * 
	 * @param st_MemberEntity MemberEntity
	 * @param st_SecuritiesAccountEntity SecuritiesAccountEntity
	 * @param first int --> 當頁起始筆數
	 * @param max int --> 每頁最大筆數
	 * @param path String --> path
	 * @return Map<String, Object>
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> selectByConditions(MemberEntity st_MemberEntity,
			SecuritiesAccountEntity st_SecuritiesAccountEntity, int first, int max, String path) {

		return stockDao.selectByConditions(st_MemberEntity, st_SecuritiesAccountEntity, first, max, path);
	}

	/**
	 * 新增股票庫存
	 * 
	 * @param stockEntity StockEntity
	 * @return StockEntity
	 */
	@Override
	@Transactional
	public StockEntity insert(StockEntity stockEntity) {

		securitiesAccountDao.updateSa_count(stockEntity.getSt_SecuritiesAccountEntity());

		stockEntity.setSt_update_time(new Date());

		return stockDao.insert(stockEntity);
	}

}
