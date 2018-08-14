/*
 * iStockage
 * File: AccountDaoImpl.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/8/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.AccountDao;
import com.istockage.model.entity.AccountEntity;
import com.istockage.model.entity.MemberEntity;

/**
 * account DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "accountDao")
public class AccountDaoImpl implements AccountDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋所有證券帳戶 (分頁)
	 * 
	 * @param ac_MemberEntity MemberEntity
	 * @param first int --> 當頁起始筆數
	 * @param max int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> selectPagination(MemberEntity ac_MemberEntity, int first, int max) {

		Map<String, Object> map = new HashMap<String, Object>();

		// outer method
		List<AccountEntity> result = (List<AccountEntity>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {

						// count
						map.put("count", session.createQuery("").getResultList().size());

						// list
						List<AccountEntity> list = session.createQuery("").setFirstResult(first).setMaxResults(max)
								.getResultList();

						return list;
					}
				});
		map.put("list", result);

		return map;
	}

}
