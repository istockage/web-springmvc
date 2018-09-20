/*
 * iStockage
 * File: SecuritiesDao.java
 * Author: 詹晟
 * Created: 2018/9/20
 * Modified: 2018/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import com.istockage.model.entity.SecuritiesEntity;

/**
 * securities DAO interface
 *
 * @author 詹晟
 */
public interface SecuritiesDao extends DaoConstant {

	/**
	 * @see com.istockage.model.dao.impl.SecuritiesDaoImpl#selectBySe_no(String)
	 */
	SecuritiesEntity selectBySe_no(String se_no);

}
