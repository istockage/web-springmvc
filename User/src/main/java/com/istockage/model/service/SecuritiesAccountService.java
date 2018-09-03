/*
 * iStockage
 * File: SecuritiesAccountService.java
 * Author: 詹晟
 * Created: 2018/8/14
 * Modified: 2018/9/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import java.util.List;
import java.util.Map;

import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.SecuritiesAccountEntity;

/**
 * securities_account service interface
 * 
 * @author 詹晟
 */
public interface SecuritiesAccountService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.SecuritiesAccountServiceImpl#selectBySa_id(Integer,
	 *      MemberEntity)
	 */
	SecuritiesAccountEntity selectBySa_id(Integer sa_id, MemberEntity memberEntity);

	/**
	 * @see com.istockage.model.service.impl.SecuritiesAccountServiceImpl#selectBySa_me_id(Integer)
	 */
	List<SecuritiesAccountEntity> selectBySa_me_id(Integer sa_me_id);

	/**
	 * @see com.istockage.model.service.impl.SecuritiesAccountServiceImpl#selectBySa_me_id(Integer,
	 *      int, int)
	 */
	Map<String, Object> selectBySa_me_id(Integer sa_me_id, int currentPage, int max);

	/**
	 * @see com.istockage.model.service.impl.SecuritiesAccountServiceImpl#insert(SecuritiesAccountEntity)
	 */
	SecuritiesAccountEntity insert(SecuritiesAccountEntity securitiesAccountEntity);

	/**
	 * @see com.istockage.model.service.impl.SecuritiesAccountServiceImpl#update(SecuritiesAccountEntity)
	 */
	SecuritiesAccountEntity update(SecuritiesAccountEntity updatedEntity);

}
