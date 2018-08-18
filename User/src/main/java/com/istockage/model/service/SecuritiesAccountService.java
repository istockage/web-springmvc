/*
 * iStockage
 * File: SecuritiesAccountService.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/8/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import java.util.Map;

/**
 * securities_account service interface
 * 
 * @author 詹晟
 */
public interface SecuritiesAccountService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.SecuritiesAccountServiceImpl#selectBySa_me_id(Integer,
	 *      int, int)
	 */
	Map<String, Object> selectBySa_me_id(Integer sa_me_id, int currentPage, int max);

}
