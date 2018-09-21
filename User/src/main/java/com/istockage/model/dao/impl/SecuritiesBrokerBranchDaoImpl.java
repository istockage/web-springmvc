/*
 * iStockage
 * File: SecuritiesBrokerBranchDaoImpl.java
 * Author: 詹晟
 * Created: 2018/8/25
 * Modified: 2018/9/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.SecuritiesBrokerBranchDao;
import com.istockage.model.entity.SecuritiesBrokerBranchEntity;

/**
 * securities_broker_branch DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "securitiesBrokerBranchDao")
public class SecuritiesBrokerBranchDaoImpl implements SecuritiesBrokerBranchDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 證券商代號搜尋
	 * 
	 * @param sb_sh_no String --> 證券商代號
	 * @return List<SecuritiesBrokerBranchEntity>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SecuritiesBrokerBranchEntity> selectBySb_sh_no(String sb_sh_no) {

		return (List<SecuritiesBrokerBranchEntity>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_SECURITIES_BROKER_BRANCH_BY_SECURITIES_BROKER_HEAD, "sb_sh_no", sb_sh_no);
	}

}
