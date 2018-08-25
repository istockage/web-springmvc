/*
 * iStockage
 * File: SecuritiesBrokerBranchServiceImpl.java
 * Author: 詹晟
 * Created: 2018/8/25
 * Modified: 2018/8/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istockage.model.dao.SecuritiesBrokerBranchDao;
import com.istockage.model.entity.SecuritiesBrokerBranchEntity;
import com.istockage.model.service.SecuritiesBrokerBranchService;

/**
 * securities_broker_branch service implement
 * 
 * @author 詹晟
 */
@Service(value = "securitiesBrokerBranchService")
public class SecuritiesBrokerBranchServiceImpl implements SecuritiesBrokerBranchService {

	/**
	 * 注入 SecuritiesBrokerBranchDao
	 */
	@Autowired
	private SecuritiesBrokerBranchDao securitiesBrokerBranchDao;

	/**
	 * 證券商流水號搜尋
	 * 
	 * @param sb_sh_id Integer --> 證券商流水號
	 * @return List<SecuritiesBrokerBranchEntity>
	 */
	@Override
	public List<SecuritiesBrokerBranchEntity> selectBySb_sh_id(Integer sb_sh_id) {

		return securitiesBrokerBranchDao.selectBySb_sh_id(sb_sh_id);
	}

}
