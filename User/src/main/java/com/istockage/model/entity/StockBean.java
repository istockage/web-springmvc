/*
 * iStockage
 * File: StockBean.java
 * Author: 詹晟
 * Date: 2018/3/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import java.util.Date;

/**
 * stock entity
 * 
 * @author 詹晟
 */
public class StockBean {

	private Integer st_id;
	private Integer st_ac_id;
	private String st_name;
	private String st_no;
	private Integer st_type_code;
	private Date st_buy_time;
	private Float st_buy_price;
	private Integer st_buy_share;
	private Integer st_buy_discount;
	private Integer st_buy_fee;
	private Integer st_buy_cost;
	private Date st_sell_time;
	private Float st_sell_price;
	private Integer st_sell_share;
	private Integer st_sell_discount;
	private Integer st_sell_fee;
	private Integer st_sell_tax;
	private Integer st_sell_revenue;

}
