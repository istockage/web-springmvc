/*
 * iStockage
 * File: UserPathDao.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/9/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import com.istockage.model.entity.UserPathEntity;

/**
 * user_path DAO interface
 *
 * @author 詹晟
 */
public interface UserPathDao extends DaoConstant {

	/**
	 * @see com.istockage.model.dao.impl.UserPathDaoImpl#selectByUp_path(String)
	 */
	UserPathEntity selectByUp_path(String up_path);

}
