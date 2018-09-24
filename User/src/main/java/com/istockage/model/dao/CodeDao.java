/*
 * iStockage
 * File: CodeDao.java
 * Author: 詹晟
 * Created: 2018/9/24
 * Modified: 2018/9/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import com.istockage.model.entity.CodeCategoryEntity;
import com.istockage.model.entity.CodeEntity;

/**
 * code DAO interface
 *
 * @author 詹晟
 */
public interface CodeDao extends DaoConstant {

	/**
	 * @see com.istockage.model.dao.impl.CodeDaoImpl#selectByCodeId(CodeCategoryEntity,
	 *      Byte)
	 */
	CodeEntity selectByCodeId(CodeCategoryEntity co_CodeCategoryEntity, Byte co_no);

}
