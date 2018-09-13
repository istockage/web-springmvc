/*
 * iStockage
 * File: StockService.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import java.util.Map;

import com.istockage.model.entity.MemberEntity;
import com.istockage.model.entity.SecuritiesAccountEntity;
import com.istockage.model.entity.StockEntity;

/**
 * stock service interface
 * 
 * @author 詹晟
 */
public interface StockService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.StockServiceImpl#selectByConditions(MemberEntity,
	 *      SecuritiesAccountEntity, int, int, String)
	 */
	Map<String, Object> selectByConditions(MemberEntity st_MemberEntity,
			SecuritiesAccountEntity st_SecuritiesAccountEntity, int first, int max, String path);

	/**
	 * @see com.istockage.model.service.impl.StockServiceImpl#insert(StockEntity)
	 */
	StockEntity insert(StockEntity stockEntity);

}
