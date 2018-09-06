/*
 * iStockage
 * File: CodeCategoryEntity.java
 * Author: 詹晟
 * Created: 2018/9/5
 * Modified: 2018/9/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * code_category entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "code_category")
public class CodeCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cc_id;
	private String cc_name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "co_CodeCategoryEntity")
	private Set<CodeEntity> cc_CodeEntity;

	public Integer getCc_id() {
		return cc_id;
	}

	public void setCc_id(Integer cc_id) {
		this.cc_id = cc_id;
	}

	public String getCc_name() {
		return cc_name;
	}

	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}

	public Set<CodeEntity> getCc_CodeEntity() {
		return cc_CodeEntity;
	}

	public void setCc_CodeEntity(Set<CodeEntity> cc_CodeEntity) {
		this.cc_CodeEntity = cc_CodeEntity;
	}

}
