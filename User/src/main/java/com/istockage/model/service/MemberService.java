/*
 * iStockage
 * File: MemberService.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/3/27
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
public interface MemberService {

	/**
	 * @see com.istockage.model.service.MemberService#signIn(String, String)
	 */
	MemberEntity signIn(String me_email, String me_password);

	/**
	 * @see com.istockage.model.service.MemberService#signUp(MemberEntity)
	 */
	MemberEntity signUp(MemberEntity memberEntity);

}
