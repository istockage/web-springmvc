/*
 * iStockage
 * File: CodeDaoImpl.java
 * Author: 詹晟
 * Created: 2018/9/24
 * Modified: 2018/9/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.CodeDao;
import com.istockage.model.entity.CodeCategoryEntity;
import com.istockage.model.entity.CodeEntity;
import com.istockage.model.entity.CodeId;

/**
 * code DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "codeDao")
public class CodeDaoImpl implements CodeDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * CodeId 搜尋
	 * 
	 * @param co_CodeCategoryEntity CodeCategoryEntity
	 * @param co_no Byte --> code 編號
	 * @return CodeEntity
	 */
	@Override
	public CodeEntity selectByCodeId(CodeCategoryEntity co_CodeCategoryEntity, Byte co_no) {

		return hibernateTemplate.get(CodeEntity.class, new CodeId(co_CodeCategoryEntity, co_no));
	}

}
