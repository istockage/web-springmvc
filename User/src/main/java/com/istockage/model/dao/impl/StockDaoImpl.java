/*
 * iStockage
 * File: StockDaoImpl.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/11
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.StockDao;
import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.SecuritiesAccountEntity;
import com.istockage.model.entity.StockEntity;

/**
 * stock DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "stockDao")
public class StockDaoImpl implements StockDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋所有股票交易明細 (分頁)
	 * 
	 * @param st_MemberEntity MemberEntity
	 * @param st_SecuritiesAccountEntity SecuritiesAccountEntity
	 * @param first int --> 當頁起始筆數
	 * @param max int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> selectByConditions(MemberEntity st_MemberEntity,
			SecuritiesAccountEntity st_SecuritiesAccountEntity, int first, int max) {

		Map<String, Object> map = new HashMap<String, Object>();

		// outer method
		List<StockEntity> result = (List<StockEntity>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {

						CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
						CriteriaQuery<StockEntity> criteriaQuery = criteriaBuilder.createQuery(StockEntity.class);

						// from
						Root<StockEntity> root = criteriaQuery.from(StockEntity.class);

						// select
						criteriaQuery.select(root);

						// where
						List<Predicate> predicates = new ArrayList<Predicate>();
						if (st_SecuritiesAccountEntity != null) {
							predicates.add(criteriaBuilder.equal(root.get("st_SecuritiesAccountEntity"),
									st_SecuritiesAccountEntity));
						}
						if (!predicates.isEmpty()) {
							criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
						}

						// order by
						criteriaQuery.orderBy(criteriaBuilder.asc(root.get("st_sell_time")));

						TypedQuery<StockEntity> typedQuery = session.createQuery(criteriaQuery);

						// count
						map.put("count", typedQuery.getResultList().size());

						// limit
						typedQuery.setFirstResult(first);
						typedQuery.setMaxResults(max);

						return typedQuery.getResultList();
					}
				});
		map.put("list", result);

		return map;
	}

	/**
	 * 新增股票交易
	 * 
	 * @param stockEntity StockEntity
	 * @return StockEntity
	 */
	@Override
	public StockEntity insert(StockEntity stockEntity) {

		hibernateTemplate.save(stockEntity);

		return stockEntity;
	}

}
