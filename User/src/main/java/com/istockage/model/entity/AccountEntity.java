/*
 * iStockage
 * File: AccountEntity.java
 * Author: 詹晟
 * Created: 2018/3/25
 * Modified: 2018/2/25
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
 * account entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "account")
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ac_id;
	@ManyToOne
	@JoinColumn(name = "ac_me_id")
	private MemberEntity ac_MemberEntity;
	@ManyToOne
	@JoinColumn(name = "ac_bb_id")
	private BrokerBranchEntity ac_BrokerBranchEntity;
	private String ac_no;
	private Byte ac_discount;
	private Date ac_update_time;

	public Integer getAc_id() {
		return ac_id;
	}

	public void setAc_id(Integer ac_id) {
		this.ac_id = ac_id;
	}

	public MemberEntity getAc_MemberEntity() {
		return ac_MemberEntity;
	}

	public void setAc_MemberEntity(MemberEntity ac_MemberEntity) {
		this.ac_MemberEntity = ac_MemberEntity;
	}

	public BrokerBranchEntity getAc_BrokerBranchEntity() {
		return ac_BrokerBranchEntity;
	}

	public void setAc_BrokerBranchEntity(BrokerBranchEntity ac_BrokerBranchEntity) {
		this.ac_BrokerBranchEntity = ac_BrokerBranchEntity;
	}

	public String getAc_no() {
		return ac_no;
	}

	public void setAc_no(String ac_no) {
		this.ac_no = ac_no;
	}

	public Byte getAc_discount() {
		return ac_discount;
	}

	public void setAc_discount(Byte ac_discount) {
		this.ac_discount = ac_discount;
	}

	public Date getAc_update_time() {
		return ac_update_time;
	}

	public void setAc_update_time(Date ac_update_time) {
		this.ac_update_time = ac_update_time;
	}

}
