/*
 * iStockage
 * File: UserPathServiceImpl.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/9/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istockage.model.dao.PathCategoryDao;
import com.istockage.model.dao.UserPathDao;
import com.istockage.model.entity.UserPathEntity;
import com.istockage.model.service.UserPathService;

/**
 * user_path service implement
 * 
 * @author 詹晟
 */
@Service(value = "userPathService")
public class UserPathServiceImpl implements UserPathService {

	/**
	 * 注入 PathCategoryDao
	 */
	@Autowired
	private PathCategoryDao pathCategoryDao;

	/**
	 * 注入 UserPathDao
	 */
	@Autowired
	private UserPathDao userPathDao;

	/**
	 * extension 及 path 搜尋
	 * 
	 * @param pc_extension String --> extension
	 * @param up_path String --> path
	 * @return null / UserPathEntity
	 */
	@Override
	@Transactional(readOnly = true)
	public UserPathEntity selectByUp_path(String pc_extension, String up_path) {

		return userPathDao.selectByUp_path(pathCategoryDao.selectByPc_extension(pc_extension), up_path);
	}

}
