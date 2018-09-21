/*
 * iStockage
 * File: SecuritiesBrokerHeadDao.java
 * Author: 詹晟
 * Created: 2018/8/25
 * Modified: 2018/9/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import java.util.List;

import com.istockage.model.entity.SecuritiesBrokerHeadEntity;

/**
 * securities_broker_head DAO interface
 *
 * @author 詹晟
 */
public interface SecuritiesBrokerHeadDao extends DaoConstant {

	/**
	 * @see com.istockage.model.dao.impl.SecuritiesBrokerHeadDaoImpl#selectByAll()
	 */
	List<SecuritiesBrokerHeadEntity> selectByAll();

	/**
	 * @see com.istockage.model.dao.impl.SecuritiesBrokerHeadDaoImpl#selectBySh_no(String)
	 */
	SecuritiesBrokerHeadEntity selectBySh_no(String sh_no);

}
