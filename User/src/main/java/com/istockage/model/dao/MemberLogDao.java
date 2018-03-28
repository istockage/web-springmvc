/*
 * iStockage
 * File: MemberLogDao.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/3/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import com.istockage.common.constant.HqlConstant;
import com.istockage.model.entity.MemberLogEntity;

/**
 * member_log DAO interface
 *
 * @author 詹晟
 */
public interface MemberLogDao extends HqlConstant {

	/**
	 * @see com.istockage.model.dao.impl.MemberLogDaoImpl#insert(MemberLogEntity)
	 */
	MemberLogEntity insert(MemberLogEntity memberLogEntity);

}
