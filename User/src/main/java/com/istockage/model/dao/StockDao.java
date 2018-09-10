/*
 * iStockage
 * File: StockDao.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/11
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import java.util.Map;

import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.SecuritiesAccountEntity;
import com.istockage.model.entity.StockEntity;

/**
 * stock DAO interface
 *
 * @author 詹晟
 */
public interface StockDao extends DaoConstant {

	/**
	 * @see com.istockage.model.dao.impl.StockDaoImpl#selectByConditions(MemberEntity,
	 *      SecuritiesAccountEntity, int, int)
	 */
	Map<String, Object> selectByConditions(MemberEntity st_MemberEntity,
			SecuritiesAccountEntity st_SecuritiesAccountEntity, int first, int max);

	/**
	 * @see com.istockage.model.dao.impl.StockDaoImpl#insert(StockEntity)
	 */
	StockEntity insert(StockEntity stockEntity);

}
