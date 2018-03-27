/*
 * iStockage
 * File: UserPathEntity.java
 * Author: 詹晟
 * Created: 2018/3/24
 * Modified: 2018/3/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer up_id;
	@ManyToOne
	@JoinColumn(name = "up_pc_id")
	private PathCategoryEntity up_PathCategoryEntity;
	private String up_name;
	private String up_path;

	public Integer getUp_id() {
		return up_id;
	}

	public void setUp_id(Integer up_id) {
		this.up_id = up_id;
	}

	public PathCategoryEntity getUp_PathCategoryEntity() {
		return up_PathCategoryEntity;
	}

	public void setUp_PathCategoryEntity(PathCategoryEntity up_PathCategoryEntity) {
		this.up_PathCategoryEntity = up_PathCategoryEntity;
	}

	public String getUp_name() {
		return up_name;
	}

	public void setUp_name(String up_name) {
		this.up_name = up_name;
	}

	public String getUp_path() {
		return up_path;
	}

	public void setUp_path(String up_path) {
		this.up_path = up_path;
	}

}
