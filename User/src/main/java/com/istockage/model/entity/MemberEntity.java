/*
 * iStockage
 * File: MemberEntity.java
 * Author: 詹晟
 * Created: 2018/3/24
 * Modified: 2018/3/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * member entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "member")
public class MemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer me_id;
	private String me_email;
	private String me_password;
	private String me_salt;
	private String me_lastname;
	private String me_firstname;
	private Date me_signup_time;
	private Date me_update_info_time;
	private Date me_update_pwd_time;

	public Integer getMe_id() {
		return me_id;
	}

	public void setMe_id(Integer me_id) {
		this.me_id = me_id;
	}

	public String getMe_email() {
		return me_email;
	}

	public void setMe_email(String me_email) {
		this.me_email = me_email;
	}

	public String getMe_password() {
		return me_password;
	}

	public void setMe_password(String me_password) {
		this.me_password = me_password;
	}

	public String getMe_salt() {
		return me_salt;
	}

	public void setMe_salt(String me_salt) {
		this.me_salt = me_salt;
	}

	public String getMe_lastname() {
		return me_lastname;
	}

	public void setMe_lastname(String me_lastname) {
		this.me_lastname = me_lastname;
	}

	public String getMe_firstname() {
		return me_firstname;
	}

	public void setMe_firstname(String me_firstname) {
		this.me_firstname = me_firstname;
	}

	public Date getMe_signup_time() {
		return me_signup_time;
	}

	public void setMe_signup_time(Date me_signup_time) {
		this.me_signup_time = me_signup_time;
	}

	public Date getMe_update_info_time() {
		return me_update_info_time;
	}

	public void setMe_update_info_time(Date me_update_info_time) {
		this.me_update_info_time = me_update_info_time;
	}

	public Date getMe_update_pwd_time() {
		return me_update_pwd_time;
	}

	public void setMe_update_pwd_time(Date me_update_pwd_time) {
		this.me_update_pwd_time = me_update_pwd_time;
	}

}
