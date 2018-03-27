/*
 * iStockage
 * File: MemberLogEntity.java
 * Author: 詹晟
 * Created: 2018/3/24
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
 * member_log entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "member_log")
public class MemberLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ml_id;
	private Date ml_insert_time;
	@ManyToOne
	@JoinColumn(name = "ml_me_id")
	private MemberEntity ml_MemberEntity;
	@ManyToOne
	@JoinColumn(name = "ml_up_id")
	private UserPathEntity ml_UserPathEntity;
	private String ml_ip;

	public Integer getMl_id() {
		return ml_id;
	}

	public void setMl_id(Integer ml_id) {
		this.ml_id = ml_id;
	}

	public Date getMl_insert_time() {
		return ml_insert_time;
	}

	public void setMl_insert_time(Date ml_insert_time) {
		this.ml_insert_time = ml_insert_time;
	}

	public MemberEntity getMl_MemberEntity() {
		return ml_MemberEntity;
	}

	public void setMl_MemberEntity(MemberEntity ml_MemberEntity) {
		this.ml_MemberEntity = ml_MemberEntity;
	}

	public UserPathEntity getMl_UserPathEntity() {
		return ml_UserPathEntity;
	}

	public void setMl_UserPathEntity(UserPathEntity ml_UserPathEntity) {
		this.ml_UserPathEntity = ml_UserPathEntity;
	}

	public String getMl_ip() {
		return ml_ip;
	}

	public void setMl_ip(String ml_ip) {
		this.ml_ip = ml_ip;
	}

}
