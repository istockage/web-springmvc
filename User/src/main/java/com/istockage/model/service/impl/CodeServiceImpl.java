/*
 * iStockage
 * File: CodeServiceImpl.java
 * Author: 詹晟
 * Created: 2018/9/24
 * Modified: 2018/9/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istockage.model.dao.CodeDao;
import com.istockage.model.entity.CodeCategoryEntity;
import com.istockage.model.entity.CodeEntity;
import com.istockage.model.service.CodeService;

/**
 * code service implement
 * 
 * @author 詹晟
 */
@Service(value = "codeService")
public class CodeServiceImpl implements CodeService {

	/**
	 * 注入 CodeDao
	 */
	@Autowired
	private CodeDao codeDao;

	/**
	 * CodeId 搜尋
	 * 
	 * @param co_CodeCategoryEntity CodeCategoryEntity
	 * @param co_no Byte --> code 編號
	 * @return CodeEntity
	 */
	@Override
	public CodeEntity selectByCodeId(CodeCategoryEntity co_CodeCategoryEntity, Byte co_no) {

		return codeDao.selectByCodeId(co_CodeCategoryEntity, co_no);
	}

}
