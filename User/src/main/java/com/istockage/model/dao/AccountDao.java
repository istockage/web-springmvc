/*
 * iStockage
 * File: AccountDao.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/8/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import java.util.Map;

/**
 * account DAO interface
 *
 * @author 詹晟
 */
public interface AccountDao extends DaoConstant {

	/**
	 * @see
	 */
	public Map<String, Object> selectPagination(int first, int max);

}
