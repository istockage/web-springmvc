/*
 * iStockage
 * File: SecuritiesBrokerHeadServiceImpl.java
 * Author: 詹晟
 * Created: 2018/8/25
 * Modified: 2018/9/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istockage.model.dao.SecuritiesBrokerHeadDao;
import com.istockage.model.entity.SecuritiesBrokerHeadEntity;
import com.istockage.model.service.SecuritiesBrokerHeadService;

/**
 * securities_broker_head service implement
 * 
 * @author 詹晟
 */
@Service(value = "securitiesBrokerHeadService")
public class SecuritiesBrokerHeadServiceImpl implements SecuritiesBrokerHeadService {

	/**
	 * 注入 SecuritiesBrokerHeadDao
	 */
	@Autowired
	private SecuritiesBrokerHeadDao securitiesBrokerHeadDao;

	/**
	 * 搜尋所有證券商
	 * 
	 * @return List<SecuritiesBrokerHeadEntity>
	 */
	@Override
	public List<SecuritiesBrokerHeadEntity> selectByAll() {

		return securitiesBrokerHeadDao.selectByAll();
	}

	/**
	 * 證券商代號搜尋
	 * 
	 * @param sh_no String --> 證券商代號
	 * @return SecuritiesBrokerHeadEntity
	 */
	@Override
	public SecuritiesBrokerHeadEntity selectBySh_no(String sh_no) {

		return securitiesBrokerHeadDao.selectBySh_no(sh_no);
	}

}
