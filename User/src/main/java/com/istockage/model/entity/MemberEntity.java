/*
 * iStockage
 * File: MemberEntity.java
 * Author: 詹晟
 * Created: 2018/3/24
 * Modified: 2018/2/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.entity;

import java.util.Date;

public class MemberEntity {

	private Integer me_id;
	private String me_email;
	private String me_password;
	private String me_salt;
	private String me_lastname;
	private String me_firstname;
	private Date me_signup_time;
	private Integer me_signin_number;
	private Date me_update_info_time;
	private Date me_update_pwd_time;

}
