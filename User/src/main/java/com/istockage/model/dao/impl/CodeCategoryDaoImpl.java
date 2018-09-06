/*
 * iStockage
 * File: CodeCategoryDaoImpl.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import java.util.List;

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
	 * 名稱搜尋
	 * 
	 * @param cc_name String --> 名稱
	 * @return null / CodeCategoryEntity
	 */
	@Override
	@SuppressWarnings("unchecked")
	public CodeCategoryEntity selectByCc_name(String cc_name) {

		List<CodeCategoryEntity> list = (List<CodeCategoryEntity>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_CODE_CATEGORY_BY_NAME, "cc_name", cc_name);

		return list.isEmpty() ? null : list.get(0);
	}

}
