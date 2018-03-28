/*
 * iStockage
 * File: UserPathDao.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/3/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import com.istockage.model.entity.PathCategoryEntity;
import com.istockage.model.entity.UserPathEntity;

/**
 * user_path DAO interface
 *
 * @author 詹晟
 */
public interface UserPathDao {

	/**
	 * @see com.istockage.model.dao.impl.UserPathDaoImpl#selectByUp_path(PathCategoryEntity,
	 *      String)
	 */
	UserPathEntity selectByUp_path(PathCategoryEntity up_PathCategoryEntity, String up_path);

}
