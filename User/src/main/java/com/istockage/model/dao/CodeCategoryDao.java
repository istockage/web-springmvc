/*
 * iStockage
 * File: CodeCategoryDao.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import com.istockage.model.entity.CodeCategoryEntity;

/**
 * code_category DAO interface
 *
 * @author 詹晟
 */
public interface CodeCategoryDao extends DaoConstant {

	/**
	 * @see com.istockage.model.dao.impl.CodeCategoryDaoImpl#selectByCc_name(String)
	 */
	CodeCategoryEntity selectByCc_name(String cc_name);

}
