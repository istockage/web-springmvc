/*
 * iStockage
 * File: UserPathEntity.java
 * Author: 詹晟
 * Created: 2018/3/24
 * Modified: 2018/9/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user_path entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "user_path")
public class UserPathEntity {

	@Id
	private String up_path;
	private String up_name;

	public String getUp_path() {
		return up_path;
	}

	public void setUp_path(String up_path) {
		this.up_path = up_path;
	}

	public String getUp_name() {
		return up_name;
	}

	public void setUp_name(String up_name) {
		this.up_name = up_name;
	}

}
