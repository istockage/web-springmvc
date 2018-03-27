/*
 * iStockage
 * File: BrokerBranchEntity.java
 * Author: 詹晟
 * Created: 2018/3/25
 * Modified: 2018/3/25
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
 * broker_branch entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "broker_branch")
public class BrokerBranchEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bb_id;
	@ManyToOne
	@JoinColumn(name = "bb_bh_id")
	private BrokerHeadEntity bb_BrokerHeadEntity;
	private String bb_no;
	private String bb_name;
	private Date bb_update_time;

	public Integer getBb_id() {
		return bb_id;
	}

	public void setBb_id(Integer bb_id) {
		this.bb_id = bb_id;
	}

	public BrokerHeadEntity getBb_BrokerHeadEntity() {
		return bb_BrokerHeadEntity;
	}

	public void setBb_BrokerHeadEntity(BrokerHeadEntity bb_BrokerHeadEntity) {
		this.bb_BrokerHeadEntity = bb_BrokerHeadEntity;
	}

	public String getBb_no() {
		return bb_no;
	}

	public void setBb_no(String bb_no) {
		this.bb_no = bb_no;
	}

	public String getBb_name() {
		return bb_name;
	}

	public void setBb_name(String bb_name) {
		this.bb_name = bb_name;
	}

	public Date getBb_update_time() {
		return bb_update_time;
	}

	public void setBb_update_time(Date bb_update_time) {
		this.bb_update_time = bb_update_time;
	}

}
