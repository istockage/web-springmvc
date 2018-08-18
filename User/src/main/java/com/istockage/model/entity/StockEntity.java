/*
 * iStockage
 * File: StockEntity.java
 * Author: 詹晟
 * Created: 2018/3/23
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@JoinColumn(name = "st_sa_id")
	private SecuritiesAccountEntity st_SecuritiesAccountEntity;
	private String st_name;
	private String st_no;
	private Integer st_type_code;
	private Date st_buy_time;
	private Float st_buy_price;
	private Integer st_buy_share;
	private Byte st_buy_discount;
	private Integer st_buy_fee;
	private Integer st_buy_cost;
	private Date st_sell_time;
	private Float st_sell_price;
	private Integer st_sell_share;
	private Byte st_sell_discount;
	private Integer st_sell_fee;
	private Integer st_sell_tax;
	private Integer st_sell_revenue;

	public Integer getSt_id() {
		return st_id;
	}

	public void setSt_id(Integer st_id) {
		this.st_id = st_id;
	}

	public SecuritiesAccountEntity getSt_SecuritiesAccountEntity() {
		return st_SecuritiesAccountEntity;
	}

	public void setSt_SecuritiesAccountEntity(SecuritiesAccountEntity st_SecuritiesAccountEntity) {
		this.st_SecuritiesAccountEntity = st_SecuritiesAccountEntity;
	}

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public String getSt_no() {
		return st_no;
	}

	public void setSt_no(String st_no) {
		this.st_no = st_no;
	}

	public Integer getSt_type_code() {
		return st_type_code;
	}

	public void setSt_type_code(Integer st_type_code) {
		this.st_type_code = st_type_code;
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

	public Integer getSt_buy_cost() {
		return st_buy_cost;
	}

	public void setSt_buy_cost(Integer st_buy_cost) {
		this.st_buy_cost = st_buy_cost;
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

	public Integer getSt_sell_revenue() {
		return st_sell_revenue;
	}

	public void setSt_sell_revenue(Integer st_sell_revenue) {
		this.st_sell_revenue = st_sell_revenue;
	}

}
