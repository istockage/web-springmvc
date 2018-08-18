/*
 * iStockage
 * File: SecuritiesBrokerHeadEntity.java
 * Author: 詹晟
 * Created: 2018/3/25
 * Modified: 2018/8/19
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
 * securities_broker_head entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "securities_broker_head")
public class SecuritiesBrokerHeadEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sh_id;
	private String sh_no;
	private String sh_name;
	private Date sh_update_time;

	public Integer getSh_id() {
		return sh_id;
	}

	public void setSh_id(Integer sh_id) {
		this.sh_id = sh_id;
	}

	public String getSh_no() {
		return sh_no;
	}

	public void setSh_no(String sh_no) {
		this.sh_no = sh_no;
	}

	public String getSh_name() {
		return sh_name;
	}

	public void setSh_name(String sh_name) {
		this.sh_name = sh_name;
	}

	public Date getSh_update_time() {
		return sh_update_time;
	}

	public void setSh_update_time(Date sh_update_time) {
		this.sh_update_time = sh_update_time;
	}

}
