/*
 * iStockage
 * File: CodeCategoryServiceImpl.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/24
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
	 * code 類別編號搜尋
	 * 
	 * @param cc_no Integer --> code 類別編號
	 * @return CodeCategoryEntity
	 */
	@Override
	public CodeCategoryEntity selectByCc_no(Integer cc_no) {

		return codeCategoryDao.selectByCc_no(cc_no);
	}

}
