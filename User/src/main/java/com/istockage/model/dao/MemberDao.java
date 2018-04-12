/*
 * iStockage
 * File: MemberDao.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/4/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao;

import com.istockage.common.constant.HqlConstant;
import com.istockage.model.entity.MemberEntity;

/**
 * member DAO interface
 *
 * @author 詹晟
 */
public interface MemberDao extends HqlConstant {

	/**
	 * @see com.istockage.model.dao.impl.MemberDaoImpl#selectByMe_email(String,
	 *      Byte)
	 */
	MemberEntity selectByMe_email(String me_email, Byte me_activity_code);

	/**
	 * @see com.istockage.model.dao.impl.MemberDaoImpl#insert(MemberEntity)
	 */
	MemberEntity insert(MemberEntity memberEntity);

	/**
	 * @see com.istockage.model.dao.impl.MemberDaoImpl#update(MemberEntity)
	 */
	MemberEntity update(MemberEntity memberEntity);

}
