/*
 * iStockage
 * File: SecuritiesEntity.java
 * Author: 詹晟
 * Created: 2018/9/19
 * Modified: 2018/9/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * securities entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "securities")
public class SecuritiesEntity {

	@Id
	private String se_no;
	private String se_name;

	public String getSe_no() {
		return se_no;
	}

	public void setSe_no(String se_no) {
		this.se_no = se_no;
	}

	public String getSe_name() {
		return se_name;
	}

	public void setSe_name(String se_name) {
		this.se_name = se_name;
	}

}
