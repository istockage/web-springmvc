/*
 * iStockage
 * File: StockEntity.java
 * Author: 詹晟
 * Created: 2018/3/23
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;

/**
 * stock entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "stock")
public class StockEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer st_id;
	@ManyToOne
	@JoinColumn(name = "st_me_id")
	private MemberEntity st_MemberEntity;
	@ManyToOne
	@JoinColumn(name = "st_sa_id")
	private SecuritiesAccountEntity st_SecuritiesAccountEntity;
	@ManyToOne
	@JoinColumn(name = "st_se_no")
	private SecuritiesEntity st_SecuritiesEntity;
	@ManyToOne
	@JoinColumn(name = "st_cc_id")
	private CodeCategoryEntity st_CodeCategoryEntity;
	@ManyToOne
	@JoinColumn(name = "st_co_id")
	private CodeEntity st_CodeEntity;
	@Past
	private Date st_buy_time;
	@Digits(integer = 4, fraction = 2)
	private Float st_buy_price;
	@Digits(integer = 9, fraction = 0)
	private Integer st_buy_share;
	@Digits(integer = 3, fraction = 0)
	@Range(min = 0, max = 100)
	private Byte st_buy_discount;
	@Digits(integer = 6, fraction = 0)
	private Integer st_buy_fee;
	@Digits(integer = 9, fraction = 0)
	private Integer st_buy_delivery;
	@Past
	private Date st_sell_time;
	@Digits(integer = 4, fraction = 2)
	private Float st_sell_price;
	@Digits(integer = 9, fraction = 0)
	private Integer st_sell_share;
	@Digits(integer = 3, fraction = 0)
	@Range(min = 0, max = 100)
	private Byte st_sell_discount;
	@Digits(integer = 6, fraction = 0)
	private Integer st_sell_fee;
	@Digits(integer = 6, fraction = 0)
	private Integer st_sell_tax;
	@Digits(integer = 9, fraction = 0)
	private Integer st_sell_delivery;
	private Date st_update_time;

	public Integer getSt_id() {
		return st_id;
	}

	public void setSt_id(Integer st_id) {
		this.st_id = st_id;
	}

	public MemberEntity getSt_MemberEntity() {
		return st_MemberEntity;
	}

	public void setSt_MemberEntity(MemberEntity st_MemberEntity) {
		this.st_MemberEntity = st_MemberEntity;
	}

	public SecuritiesAccountEntity getSt_SecuritiesAccountEntity() {
		return st_SecuritiesAccountEntity;
	}

	public void setSt_SecuritiesAccountEntity(SecuritiesAccountEntity st_SecuritiesAccountEntity) {
		this.st_SecuritiesAccountEntity = st_SecuritiesAccountEntity;
	}

	public SecuritiesEntity getSt_SecuritiesEntity() {
		return st_SecuritiesEntity;
	}

	public void setSt_SecuritiesEntity(SecuritiesEntity st_SecuritiesEntity) {
		this.st_SecuritiesEntity = st_SecuritiesEntity;
	}

	public CodeCategoryEntity getSt_CodeCategoryEntity() {
		return st_CodeCategoryEntity;
	}

	public void setSt_CodeCategoryEntity(CodeCategoryEntity st_CodeCategoryEntity) {
		this.st_CodeCategoryEntity = st_CodeCategoryEntity;
	}

	public CodeEntity getSt_CodeEntity() {
		return st_CodeEntity;
	}

	public void setSt_CodeEntity(CodeEntity st_CodeEntity) {
		this.st_CodeEntity = st_CodeEntity;
	}

	public Date getSt_buy_time() {
		return st_buy_time;
	}

	public void setSt_buy_time(Date st_buy_time) {
		this.st_buy_time = st_buy_time;
	}

	public Float getSt_buy_price() {
		return st_buy_price;
	}

	public void setSt_buy_price(Float st_buy_price) {
		this.st_buy_price = st_buy_price;
	}

	public Integer getSt_buy_share() {
		return st_buy_share;
	}

	public void setSt_buy_share(Integer st_buy_share) {
		this.st_buy_share = st_buy_share;
	}

	public Byte getSt_buy_discount() {
		return st_buy_discount;
	}

	public void setSt_buy_discount(Byte st_buy_discount) {
		this.st_buy_discount = st_buy_discount;
	}

	public Integer getSt_buy_fee() {
		return st_buy_fee;
	}

	public void setSt_buy_fee(Integer st_buy_fee) {
		this.st_buy_fee = st_buy_fee;
	}

	public Integer getSt_buy_delivery() {
		return st_buy_delivery;
	}

	public void setSt_buy_delivery(Integer st_buy_delivery) {
		this.st_buy_delivery = st_buy_delivery;
	}

	public Date getSt_sell_time() {
		return st_sell_time;
	}

	public void setSt_sell_time(Date st_sell_time) {
		this.st_sell_time = st_sell_time;
	}

	public Float getSt_sell_price() {
		return st_sell_price;
	}

	public void setSt_sell_price(Float st_sell_price) {
		this.st_sell_price = st_sell_price;
	}

	public Integer getSt_sell_share() {
		return st_sell_share;
	}

	public void setSt_sell_share(Integer st_sell_share) {
		this.st_sell_share = st_sell_share;
	}

	public Byte getSt_sell_discount() {
		return st_sell_discount;
	}

	public void setSt_sell_discount(Byte st_sell_discount) {
		this.st_sell_discount = st_sell_discount;
	}

	public Integer getSt_sell_fee() {
		return st_sell_fee;
	}

	public void setSt_sell_fee(Integer st_sell_fee) {
		this.st_sell_fee = st_sell_fee;
	}

	public Integer getSt_sell_tax() {
		return st_sell_tax;
	}

	public void setSt_sell_tax(Integer st_sell_tax) {
		this.st_sell_tax = st_sell_tax;
	}

	public Integer getSt_sell_delivery() {
		return st_sell_delivery;
	}

	public void setSt_sell_delivery(Integer st_sell_delivery) {
		this.st_sell_delivery = st_sell_delivery;
	}

	public Date getSt_update_time() {
		return st_update_time;
	}

	public void setSt_update_time(Date st_update_time) {
		this.st_update_time = st_update_time;
	}

}
