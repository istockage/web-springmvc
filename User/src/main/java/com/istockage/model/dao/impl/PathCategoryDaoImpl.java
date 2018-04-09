/*
 * iStockage
 * File: PathCategoryDaoImpl.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/4/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.PathCategoryDao;
import com.istockage.model.entity.PathCategoryEntity;

/**
 * path_category DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "pathCategoryDao")
public class PathCategoryDaoImpl implements PathCategoryDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * extension 搜尋
	 * 
	 * @param pc_extension
	 *            String --> extension
	 * @return null / PathCategoryEntity
	 */
	@Override
	@SuppressWarnings("unchecked")
	public PathCategoryEntity selectByPc_extension(String pc_extension) {

		List<PathCategoryEntity> list = (List<PathCategoryEntity>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_PATH_CATEGORY_BY_EXTENSION, "pc_extension", pc_extension);

		return list.isEmpty() ? null : list.get(0);
	}

}
