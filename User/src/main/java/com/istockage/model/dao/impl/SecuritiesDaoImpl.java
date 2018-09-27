/*
 * iStockage
 * File: SecuritiesDaoImpl.java
 * Author: 詹晟
 * Created: 2018/9/20
 * Modified: 2018/9/27
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

import com.istockage.model.dao.SecuritiesDao;
import com.istockage.model.entity.SecuritiesEntity;

/**
 * securities DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "securitiesDao")
public class SecuritiesDaoImpl implements SecuritiesDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 股票代號搜尋
	 * 
	 * @param se_no String --> 股票代號
	 * @return SecuritiesEntity
	 */
	@Override
	public SecuritiesEntity selectBySe_no(String se_no) {

		return hibernateTemplate.get(SecuritiesEntity.class, se_no);
	}

	/**
	 * 股票代號或名稱搜尋
	 * 
	 * @param search String --> 股票代號或名稱
	 * @return List<SecuritiesEntity>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SecuritiesEntity> selectLikeBySe_noOrSe_name(String search) {

		DetachedCriteria criteria = DetachedCriteria.forClass(SecuritiesEntity.class);

		if (search != null) {
			criteria.add(Restrictions.or(Restrictions.like("se_no", "%" + search + "%"),
					Restrictions.like("se_name", "%" + search + "%")));
		}

		return (List<SecuritiesEntity>) hibernateTemplate.findByCriteria(criteria);
	}

}
