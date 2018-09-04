/*
 * iStockage
 * File: CodeEntity.java
 * Author: 詹晟
 * Created: 2018/9/5
 * Modified: 2018/9/5
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
 * code entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "code")
public class CodeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer co_id;
	@ManyToOne
	@JoinColumn(name = "co_cc_id")
	private CodeCategoryEntity co_CodeCategoryEntity;
	private Byte co_no;
	private String co_name;

	public Integer getCo_id() {
		return co_id;
	}

	public void setCo_id(Integer co_id) {
		this.co_id = co_id;
	}

	public CodeCategoryEntity getCo_CodeCategoryEntity() {
		return co_CodeCategoryEntity;
	}

	public void setCo_CodeCategoryEntity(CodeCategoryEntity co_CodeCategoryEntity) {
		this.co_CodeCategoryEntity = co_CodeCategoryEntity;
	}

	public Byte getCo_no() {
		return co_no;
	}

	public void setCo_no(Byte co_no) {
		this.co_no = co_no;
	}

	public String getCo_name() {
		return co_name;
	}

	public void setCo_name(String co_name) {
		this.co_name = co_name;
	}

}
