/*
 * iStockage
 * File: UserPathService.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/4/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import com.istockage.model.entity.UserPathEntity;

/**
 * user_path service interface
 * 
 * @author 詹晟
 */
public interface UserPathService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.UserPathServiceImpl#selectByUp_path(String,
	 *      String)
	 */
	UserPathEntity selectByUp_path(String pc_extension, String up_path);

}
