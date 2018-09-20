/*
 * iStockage
 * File: SecuritiesEntity.java
 * Author: 詹晟
 * Created: 2018/9/19
 * Modified: 2018/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * securities entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "securities")
public class SecuritiesEntity {

	@Id
	@Pattern(regexp = "^[0-9a-zA-Z]{4,6}$")
	private String se_no;
	@NotBlank
	@Size(max = 10)
	private String se_name;
	private Date se_update_time;

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

	public Date getSe_update_time() {
		return se_update_time;
	}

	public void setSe_update_time(Date se_update_time) {
		this.se_update_time = se_update_time;
	}

}
