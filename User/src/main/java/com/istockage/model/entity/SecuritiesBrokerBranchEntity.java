/*
 * iStockage
 * File: SecuritiesBrokerBranchEntity.java
 * Author: 詹晟
 * Created: 2018/3/25
 * Modified: 2018/9/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * securities_broker_branch entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "securities_broker_branch")
@IdClass(SecuritiesBrokerBranchId.class)
public class SecuritiesBrokerBranchEntity {

	@Id
	@ManyToOne
	@JoinColumn(name = "sb_sh_no")
	private SecuritiesBrokerHeadEntity sb_SecuritiesBrokerHeadEntity;
	@Id
	@Expose
	private String sb_no;
	@Expose
	private String sb_name;
	private Date sb_update_time;

	public SecuritiesBrokerHeadEntity getSb_SecuritiesBrokerHeadEntity() {
		return sb_SecuritiesBrokerHeadEntity;
	}

	public void setSb_SecuritiesBrokerHeadEntity(SecuritiesBrokerHeadEntity sb_SecuritiesBrokerHeadEntity) {
		this.sb_SecuritiesBrokerHeadEntity = sb_SecuritiesBrokerHeadEntity;
	}

	public String getSb_no() {
		return sb_no;
	}

	public void setSb_no(String sb_no) {
		this.sb_no = sb_no;
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
