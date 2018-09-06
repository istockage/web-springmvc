/*
 * iStockage
 * File: StockService.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import com.istockage.model.entity.StockEntity;

/**
 * stock service interface
 * 
 * @author 詹晟
 */
public interface StockService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.StockServiceImpl#insert(StockEntity)
	 */
	StockEntity insert(StockEntity stockEntity);

}
