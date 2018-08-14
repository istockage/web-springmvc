/*
 * iStockage
 * File: AccountServiceImpl.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/8/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istockage.model.dao.AccountDao;
import com.istockage.model.service.AccountService;

/**
 * account service implement
 * 
 * @author 詹晟
 */
@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

	/**
	 * 注入 AccountDao
	 */
	@Autowired
	private AccountDao accountDao;

	/**
	 * 搜尋所有證券帳戶 (分頁)
	 * 
	 * @param ac_me_id Integer --> 會員流水號
	 * @param page Integer --> 當前頁碼
	 * @param max int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> selectByAc_me_id(Integer ac_me_id, Integer page, int max) {

		// 取得當頁起始筆數
		int first = (page - 1) * max;

		return accountDao.selectByAc_me_id(ac_me_id, first, max);
	}

}
