/*
 * iStockage
 * File: MemberService.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/4/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import com.istockage.model.entity.MemberEntity;

/**
 * member service interface
 * 
 * @author 詹晟
 */
public interface MemberService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.MemberService#signIn(String, String)
	 */
	MemberEntity signIn(String me_email, String me_password);

	/**
	 * @see com.istockage.model.service.MemberService#signUp(MemberEntity)
	 */
	MemberEntity signUp(MemberEntity memberEntity);

	/**
	 * @see com.istockage.model.service.MemberService#selectByMe_email(String, Byte)
	 */
	MemberEntity selectByMe_email(String me_email, Byte me_activity_code);

	/**
	 * @see com.istockage.model.service.MemberService#updateMe_password(MemberEntity,
	 *      String)
	 */
	MemberEntity updateMe_password(MemberEntity memberEntity, String me_password_new);

	/**
	 * @see com.istockage.model.service.MemberService#updateMe_activity_code(String)
	 */
	MemberEntity updateMe_activity_code(String me_no);

}
