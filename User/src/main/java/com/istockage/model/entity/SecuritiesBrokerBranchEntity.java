/*
 * iStockage
 * File: SecuritiesBrokerBranchEntity.java
 * Author: 詹晟
 * Created: 2018/3/25
 * Modified: 2018/9/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * securities_broker_branch entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "securities_broker_branch")
public class SecuritiesBrokerBranchEntity {

	@EmbeddedId
	private SecuritiesBrokerBranchId securitiesBrokerBranchId;
	private String sb_name;
	private Date sb_update_time;

	public SecuritiesBrokerBranchId getSecuritiesBrokerBranchId() {
		return securitiesBrokerBranchId;
	}

	public void setSecuritiesBrokerBranchId(SecuritiesBrokerBranchId securitiesBrokerBranchId) {
		this.securitiesBrokerBranchId = securitiesBrokerBranchId;
	}

	public String getSb_name() {
		return sb_name;
	}

	public void setSb_name(String sb_name) {
		this.sb_name = sb_name;
	}

	public Date getSb_update_time() {
		return sb_update_time;
	}

	public void setSb_update_time(Date sb_update_time) {
		this.sb_update_time = sb_update_time;
	}

}
