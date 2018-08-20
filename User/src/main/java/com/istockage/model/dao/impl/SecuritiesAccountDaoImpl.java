/*
 * iStockage
 * File: SecuritiesAccountDaoImpl.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/8/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.SecuritiesAccountDao;
import com.istockage.model.entity.SecuritiesAccountEntity;

/**
 * securities_account DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "securitiesAccountDao")
public class SecuritiesAccountDaoImpl implements SecuritiesAccountDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋所有證券帳戶 (分頁)
	 * 
	 * @param sa_me_id Integer --> 會員流水號
	 * @param first int --> 當頁起始筆數
	 * @param max int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> selectBySa_me_id(Integer sa_me_id, int first, int max) {

		Map<String, Object> map = new HashMap<String, Object>();

		// outer method
		List<SecuritiesAccountEntity> result = (List<SecuritiesAccountEntity>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {

						Query query = session.createQuery(HQL_SELECT_SECURITIES_ACCOUNT_BY_MEMBER)
								.setParameter("sa_me_id", sa_me_id);

						// count
						map.put("count", query.getResultList().size());

						// list
						List<SecuritiesAccountEntity> list = query.setFirstResult(first).setMaxResults(max)
								.getResultList();

						return list;
					}
				});
		map.put("list", result);

		return map;
	}

}