/*
 * iStockage
 * File: CodeCategoryDaoImpl.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.CodeCategoryDao;
import com.istockage.model.entity.CodeCategoryEntity;

/**
 * code_category DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "codeCategoryDao")
public class CodeCategoryDaoImpl implements CodeCategoryDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * code 類別編號搜尋
	 * 
	 * @param cc_no Integer --> code 類別編號
	 * @return CodeCategoryEntity
	 */
	@Override
	public CodeCategoryEntity selectByCc_no(Integer cc_no) {

		return hibernateTemplate.get(CodeCategoryEntity.class, cc_no);
	}

}
