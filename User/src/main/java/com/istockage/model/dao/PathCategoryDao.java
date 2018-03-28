/*
 * iStockage
 * File: PathCategoryDao.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/3/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import com.istockage.common.constant.HqlConstant;
import com.istockage.model.entity.PathCategoryEntity;

/**
 * path_category DAO interface
 * 
 * @author 詹晟
 */
public interface PathCategoryDao extends HqlConstant {

	/**
	 * @see com.istockage.model.dao.impl.PathCategoryDaoImpl#selectByPc_extension(String)
	 */
	PathCategoryEntity selectByPc_extension(String pc_extension);

}
