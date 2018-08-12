/*
 * iStockage
 * File: MemberEntity.java
 * Author: 詹晟
 * Created: 2018/3/24
 * Modified: 2018/8/12
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	private String me_no;
	@NotEmpty
	@Email
	@Size(max = 50)
	private String me_email;
	@Pattern(regexp = "^(?=.*([a-z]|[A-Z]))(?=.*[0-9])(?=\\S+$).{8,32}$")
	private String me_password;
	private String me_salt;
	private String me_random;
	@Size(max = 20)
	private String me_lastname;
	@Size(max = 20)
	private String me_firstname;
	private Byte me_activity_code;
	private Date me_signup_time;
	private Date me_update_info_time;
	private Date me_update_pwd_time;

	public Integer getMe_id() {
		return me_id;
	}

	public void setMe_id(Integer me_id) {
		this.me_id = me_id;
	}

	public String getMe_no() {
		return me_no;
	}

	public void setMe_no(String me_no) {
		this.me_no = me_no;
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

	public String getMe_random() {
		return me_random;
	}

	public void setMe_random(String me_random) {
		this.me_random = me_random;
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

	public Byte getMe_activity_code() {
		return me_activity_code;
	}

	public void setMe_activity_code(Byte me_activity_code) {
		this.me_activity_code = me_activity_code;
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
