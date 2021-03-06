/*
 * iStockage
 * File: SecuritiesAccountDao.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/10/4
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import java.util.List;
import java.util.Map;

import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.SecuritiesAccountEntity;

/**
 * securities_account DAO interface
 *
 * @author 詹晟
 */
public interface SecuritiesAccountDao extends DaoConstant {

	/**
	 * @see com.istockage.model.dao.impl.SecuritiesAccountDaoImpl#selectBySa_id(Integer,
	 *      MemberEntity)
	 */
	SecuritiesAccountEntity selectBySa_id(Integer sa_id, MemberEntity sa_MemberEntity);

	/**
	 * @see com.istockage.model.dao.impl.SecuritiesAccountDaoImpl#selectBySa_me_id(Integer)
	 */
	List<SecuritiesAccountEntity> selectBySa_me_id(Integer sa_me_id);

	/**
	 * @see com.istockage.model.dao.impl.SecuritiesAccountDaoImpl#selectBySa_me_id(Integer,
	 *      int, int)
	 */
	Map<String, Object> selectBySa_me_id(Integer sa_me_id, int first, int max);

	/**
	 * @see com.istockage.model.dao.impl.SecuritiesAccountDaoImpl#insert(SecuritiesAccountEntity)
	 */
	SecuritiesAccountEntity insert(SecuritiesAccountEntity securitiesAccountEntity);

	/**
	 * @see com.istockage.model.dao.impl.SecuritiesAccountDaoImpl#update(SecuritiesAccountEntity)
	 */
	SecuritiesAccountEntity update(SecuritiesAccountEntity updatedEntity);

	/**
	 * @see com.istockage.model.dao.impl.SecuritiesAccountDaoImpl#updateSa_count(SecuritiesAccountEntity)
	 */
	SecuritiesAccountEntity updateSa_count(SecuritiesAccountEntity updatedEntity);

}
