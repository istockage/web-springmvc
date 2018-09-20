/*
 * iStockage
 * File: SecuritiesServiceImpl.java
 * Author: 詹晟
 * Created: 2018/9/20
 * Modified: 2018/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istockage.model.dao.SecuritiesDao;
import com.istockage.model.entity.SecuritiesEntity;
import com.istockage.model.service.SecuritiesService;

/**
 * securities service implement
 * 
 * @author 詹晟
 */
@Service(value = "securitiesService")
public class SecuritiesServiceImpl implements SecuritiesService {

	/**
	 * 注入 SecuritiesDao
	 */
	@Autowired
	private SecuritiesDao securitiesDao;

	/**
	 * 股票代號搜尋
	 * 
	 * @param se_no String --> 股票代號
	 * @return SecuritiesEntity
	 */
	@Override
	public SecuritiesEntity selectBySe_no(String se_no) {

		return securitiesDao.selectBySe_no(se_no);
	}

}
