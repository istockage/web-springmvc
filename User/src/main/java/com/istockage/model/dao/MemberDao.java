/*
 * iStockage
 * File: MemberDao.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/3/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import com.istockage.model.entity.MemberEntity;

/**
 * member DAO interface
 *
 * @author 詹晟
 */
public interface MemberDao {

	/**
	 * @see com.istockage.model.dao.impl.MemberDaoImpl#selectByMe_email(String)
	 */
	MemberEntity selectByMe_email(String me_email);

	/**
	 * @see com.istockage.model.dao.impl.MemberDaoImpl#insert(MemberEntity)
	 */
	MemberEntity insert(MemberEntity memberEntity);

}
