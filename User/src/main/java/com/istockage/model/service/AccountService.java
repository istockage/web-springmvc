/*
 * iStockage
 * File: AccountService.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/8/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import java.util.Map;

/**
 * account service interface
 * 
 * @author 詹晟
 */
public interface AccountService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.AccountServiceImpl#selectByAc_me_id(Integer,
	 *      Integer, int)
	 */
	Map<String, Object> selectByAc_me_id(Integer ac_me_id, Integer page, int max);

}
