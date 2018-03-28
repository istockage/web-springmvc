/*
 * iStockage
 * File: UserPathDaoImpl.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/3/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.UserPathDao;
import com.istockage.model.entity.PathCategoryEntity;
import com.istockage.model.entity.UserPathEntity;

/**
 * user_path DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "userPathDao")
public class UserPathDaoImpl implements UserPathDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * path 類別及 path 搜尋
	 * 
	 * @param up_PathCategoryEntity
	 *            PathCategoryEntity --> path 類別
	 * @param up_path
	 *            String --> path
	 * @return null / UserPathEntity
	 */
	@Override
	@SuppressWarnings("unchecked")
	public UserPathEntity selectByUp_path(PathCategoryEntity up_PathCategoryEntity, String up_path) {

		DetachedCriteria criteria = DetachedCriteria.forClass(UserPathEntity.class);

		criteria.add(Restrictions.eq("up_PathCategoryEntity", up_PathCategoryEntity));
		criteria.add(Restrictions.eq("up_path", up_path));

		List<UserPathEntity> list = (List<UserPathEntity>) hibernateTemplate.findByCriteria(criteria);

		return list.isEmpty() ? null : list.get(0);
	}

}
