/*
 * iStockage
 * File: CodeCategoryServiceImpl.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istockage.model.dao.CodeCategoryDao;
import com.istockage.model.entity.CodeCategoryEntity;
import com.istockage.model.service.CodeCategoryService;

/**
 * code_category service implement
 * 
 * @author 詹晟
 */
@Service(value = "codeCategoryService")
public class CodeCategoryServiceImpl implements CodeCategoryService {

	/**
	 * 注入 CodeCategoryDao
	 */
	@Autowired
	private CodeCategoryDao codeCategoryDao;

	/**
	 * 名稱搜尋
	 * 
	 * @param cc_name String --> 名稱
	 * @return null / CodeCategoryEntity
	 */
	@Override
	public CodeCategoryEntity selectByCc_name(String cc_name) {

		return codeCategoryDao.selectByCc_name(cc_name);
	}

}
