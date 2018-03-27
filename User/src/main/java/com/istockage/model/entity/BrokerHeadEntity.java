/*
 * iStockage
 * File: BrokerHeadEntity.java
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
import javax.persistence.Table;

/**
 * broker_head entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "broker_head")
public class BrokerHeadEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bh_id;
	private String bh_no;
	private String bh_name;
	private Date bh_update_time;

	public Integer getBh_id() {
		return bh_id;
	}

	public void setBh_id(Integer bh_id) {
		this.bh_id = bh_id;
	}

	public String getBh_no() {
		return bh_no;
	}

	public void setBh_no(String bh_no) {
		this.bh_no = bh_no;
	}

	public String getBh_name() {
		return bh_name;
	}

	public void setBh_name(String bh_name) {
		this.bh_name = bh_name;
	}

	public Date getBh_update_time() {
		return bh_update_time;
	}

	public void setBh_update_time(Date bh_update_time) {
		this.bh_update_time = bh_update_time;
	}

}
