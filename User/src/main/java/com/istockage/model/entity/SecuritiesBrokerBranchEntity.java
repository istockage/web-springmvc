/*
 * iStockage
 * File: SecuritiesBrokerBranchEntity.java
 * Author: 詹晟
 * Created: 2018/3/25
 * Modified: 2018/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * securities_broker_branch entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "securities_broker_branch")
public class SecuritiesBrokerBranchEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sb_id;
	@ManyToOne
	@JoinColumn(name = "sb_sh_no")
	private SecuritiesBrokerHeadEntity sb_SecuritiesBrokerHeadEntity;
	private String sb_no;
	private String sb_name;
	private Date sb_update_time;

	public Integer getSb_id() {
		return sb_id;
	}

	public void setSb_id(Integer sb_id) {
		this.sb_id = sb_id;
	}

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
