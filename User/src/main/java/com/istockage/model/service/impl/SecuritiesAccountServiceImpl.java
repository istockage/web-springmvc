/*
 * iStockage
 * File: SecuritiesAccountServiceImpl.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/8/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istockage.model.dao.SecuritiesAccountDao;
import com.istockage.model.entity.SecuritiesAccountEntity;
import com.istockage.model.service.SecuritiesAccountService;

/**
 * securities_account service implement
 * 
 * @author 詹晟
 */
@Service(value = "securitiesAccountService")
public class SecuritiesAccountServiceImpl implements SecuritiesAccountService {

	/**
	 * 注入 SecuritiesAccountDao
	 */
	@Autowired
	private SecuritiesAccountDao securitiesAccountDao;

	/**
	 * 搜尋所有證券帳戶 (分頁)
	 * 
	 * @param sa_me_id Integer --> 會員流水號
	 * @param currentPage int --> 當前頁碼
	 * @param max int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> selectBySa_me_id(Integer sa_me_id, int currentPage, int max) {

		// 取得當頁起始筆數
		int first = (currentPage - 1) * max;

		return securitiesAccountDao.selectBySa_me_id(sa_me_id, first, max);
	}

	/**
	 * 新增證券帳戶
	 * 
	 * @param securitiesAccountEntity SecuritiesAccountEntity
	 * @return SecuritiesAccountEntity
	 */
	@Override
	public SecuritiesAccountEntity insert(SecuritiesAccountEntity securitiesAccountEntity) {

		return securitiesAccountDao.insert(securitiesAccountEntity);
	}

}
