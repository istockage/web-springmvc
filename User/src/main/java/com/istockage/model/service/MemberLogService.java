/*
 * iStockage
 * File: MemberLogService.java
 * Author: 詹晟
 * Created: 2018/3/28
 * Modified: 2018/4/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import com.istockage.common.constant.CodeConstant;
import com.istockage.model.entity.MemberLogEntity;

/**
 * member_log service interface
 * 
 * @author 詹晟
 */
public interface MemberLogService extends CodeConstant {

	/**
	 * @see com.istockage.model.service.impl.MemberLogServiceImpl#insert(MemberLogEntity)
	 */
	MemberLogEntity insert(MemberLogEntity memberLogEntity);

}
