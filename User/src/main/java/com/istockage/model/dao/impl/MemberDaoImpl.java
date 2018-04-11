/*
 * iStockage
 * File: MemberDaoImpl.java
 * Author: 詹晟
 * Created: 2018/3/27
 * Modified: 2018/4/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.istockage.model.dao.MemberDao;
import com.istockage.model.entity.MemberEntity;

/**
 * member DAO implement
 *
 * @author 詹晟
 */
@Repository(value = "memberDao")
public class MemberDaoImpl implements MemberDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 會員信箱搜尋
	 * 
	 * @param me_email
	 *            String --> 會員信箱
	 * @param me_activity
	 *            Byte --> 啟用狀態
	 * @return null / MemberEntity
	 */
	@Override
	@SuppressWarnings("unchecked")
	public MemberEntity selectByMe_email(String me_email, Byte me_activity) {

		DetachedCriteria criteria = DetachedCriteria.forClass(MemberEntity.class);

		criteria.add(Restrictions.eq("me_email", me_email));

		if (me_activity != null) {
			criteria.add(Restrictions.eq("me_activity", me_activity));
		}

		List<MemberEntity> list = (List<MemberEntity>) hibernateTemplate.findByCriteria(criteria);

		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * 新增會員
	 * 
	 * @param memberEntity
	 *            MemberEntity
	 * @return MemberEntity
	 */
	@Override
	public MemberEntity insert(MemberEntity memberEntity) {

		hibernateTemplate.save(memberEntity);

		return memberEntity;
	}

	/**
	 * 修改資料
	 * 
	 * @param memberEntity
	 *            MemberEntity
	 * @return MemberEntity
	 */
	@Override
	public MemberEntity update(MemberEntity memberEntity) {

		hibernateTemplate.clear();
		hibernateTemplate.update(memberEntity);

		return memberEntity;
	}

}
