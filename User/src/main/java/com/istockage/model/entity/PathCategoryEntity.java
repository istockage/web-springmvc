/*
 * iStockage
 * File: PathCategoryEntity.java
 * Author: 詹晟
 * Created: 2018/3/24
 * Modified: 2018/2/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * path_category entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "path_category")
public class PathCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pc_id;
	private String pc_name;
	private String pc_extension;

	public Integer getPc_id() {
		return pc_id;
	}

	public void setPc_id(Integer pc_id) {
		this.pc_id = pc_id;
	}

	public String getPc_name() {
		return pc_name;
	}

	public void setPc_name(String pc_name) {
		this.pc_name = pc_name;
	}

	public String getPc_extension() {
		return pc_extension;
	}

	public void setPc_extension(String pc_extension) {
		this.pc_extension = pc_extension;
	}

}
