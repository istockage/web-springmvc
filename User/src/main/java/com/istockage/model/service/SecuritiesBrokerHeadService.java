/*
 * iStockage
 * File: SecuritiesBrokerHeadService.java
 * Author: 詹晟
 * Created: 2018/8/25
 * Modified: 2018/9/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import java.util.List;

import com.istockage.model.entity.SecuritiesBrokerHeadEntity;

/**
 * securities_broker_head service interface
 * 
 * @author 詹晟
 */
public interface SecuritiesBrokerHeadService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.SecuritiesBrokerHeadServiceImpl#selectByAll()
	 */
	List<SecuritiesBrokerHeadEntity> selectByAll();

	/**
	 * @see com.istockage.model.service.impl.SecuritiesBrokerHeadServiceImpl#selectBySh_no(String)
	 */
	SecuritiesBrokerHeadEntity selectBySh_no(String sh_no);

}
