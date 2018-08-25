/*
 * iStockage
 * File: SecuritiesBrokerBranchService.java
 * Author: 詹晟
 * Created: 2018/8/25
 * Modified: 2018/8/25
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import java.util.List;

import com.istockage.model.entity.SecuritiesBrokerBranchEntity;

/**
 * securities_broker_branch service interface
 * 
 * @author 詹晟
 */
public interface SecuritiesBrokerBranchService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.SecuritiesBrokerBranchServiceImpl#selectBySb_sh_id(Integer)
	 */
	List<SecuritiesBrokerBranchEntity> selectBySb_sh_id(Integer sb_sh_id);

}
