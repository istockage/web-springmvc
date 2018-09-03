/*
 * iStockage
 * File: SecuritiesAccountServiceImpl.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/9/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istockage.model.dao.SecuritiesAccountDao;
import com.istockage.model.entity.MemberEntity;
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
	 * 證券帳戶流水號搜尋
	 * 
	 * @param sa_id Integer --> 證券帳戶流水號
	 * @param memberEntity MemberEntity
	 * @return SecuritiesAccountEntity
	 */
	@Override
	@Transactional(readOnly = true)
	public SecuritiesAccountEntity selectBySa_id(Integer sa_id, MemberEntity memberEntity) {

		return securitiesAccountDao.selectBySa_id(sa_id, memberEntity);
	}

	/**
	 * 搜尋所有證券帳戶
	 * 
	 * @param sa_me_id Integer --> 會員流水號
	 * @return List<SecuritiesAccountEntity>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SecuritiesAccountEntity> selectBySa_me_id(Integer sa_me_id) {

		return securitiesAccountDao.selectBySa_me_id(sa_me_id);
	}

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
	@Transactional
	public SecuritiesAccountEntity insert(SecuritiesAccountEntity securitiesAccountEntity) {

		securitiesAccountEntity.setSa_times(0);
		securitiesAccountEntity.setSa_update_time(new Date());

		return securitiesAccountDao.insert(securitiesAccountEntity);
	}

	/**
	 * 編輯證券帳戶
	 * 
	 * @param updatedEntity SecuritiesAccountEntity
	 * @return SecuritiesAccountEntity
	 */
	@Override
	@Transactional
	public SecuritiesAccountEntity update(SecuritiesAccountEntity updatedEntity) {

		return securitiesAccountDao.update(updatedEntity);
	}

}
