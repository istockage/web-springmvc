/*
 * iStockage
 * File: SecuritiesAccountEntity.java
 * Author: 詹晟
 * Created: 2018/3/25
 * Modified: 2018/9/21
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

/**
 * securities_account entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "securities_account")
public class SecuritiesAccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sa_id;
	@ManyToOne
	@JoinColumn(name = "sa_me_id")
	private MemberEntity sa_MemberEntity;
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "sa_sb_sh_no"), @JoinColumn(name = "sa_sb_no") })
	private SecuritiesBrokerBranchEntity sa_SecuritiesBrokerBranchEntity;
	@Pattern(regexp = "^[0-9]{7}$")
	private String sa_no;
	@Digits(integer = 3, fraction = 0)
	@Range(min = 0, max = 100)
	private Byte sa_discount;
	private Integer sa_count;
	private Date sa_update_time;

	public Integer getSa_id() {
		return sa_id;
	}

	public void setSa_id(Integer sa_id) {
		this.sa_id = sa_id;
	}

	public MemberEntity getSa_MemberEntity() {
		return sa_MemberEntity;
	}

	public void setSa_MemberEntity(MemberEntity sa_MemberEntity) {
		this.sa_MemberEntity = sa_MemberEntity;
	}

	public SecuritiesBrokerBranchEntity getSa_SecuritiesBrokerBranchEntity() {
		return sa_SecuritiesBrokerBranchEntity;
	}

	public void setSa_SecuritiesBrokerBranchEntity(SecuritiesBrokerBranchEntity sa_SecuritiesBrokerBranchEntity) {
		this.sa_SecuritiesBrokerBranchEntity = sa_SecuritiesBrokerBranchEntity;
	}

	public String getSa_no() {
		return sa_no;
	}

	public void setSa_no(String sa_no) {
		this.sa_no = sa_no;
	}

	public Byte getSa_discount() {
		return sa_discount;
	}

	public void setSa_discount(Byte sa_discount) {
		this.sa_discount = sa_discount;
	}

	public Integer getSa_count() {
		return sa_count;
	}

	public void setSa_count(Integer sa_count) {
		this.sa_count = sa_count;
	}

	public Date getSa_update_time() {
		return sa_update_time;
	}

	public void setSa_update_time(Date sa_update_time) {
		this.sa_update_time = sa_update_time;
	}

}
