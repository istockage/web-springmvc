/*
 * iStockage
 * File: SecuritiesAccountDao.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/8/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import java.util.Map;

/**
 * securities_account DAO interface
 *
 * @author 詹晟
 */
public interface SecuritiesAccountDao extends DaoConstant {

	/**
	 * @see com.istockage.model.dao.impl.SecuritiesAccountDaoImpl#selectBySa_me_id(Integer,
	 *      int, int)
	 */
	Map<String, Object> selectBySa_me_id(Integer sa_me_id, int first, int max);

}
