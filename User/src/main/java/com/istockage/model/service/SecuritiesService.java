/*
 * iStockage
 * File: SecuritiesService.java
 * Author: 詹晟
 * Created: 2018/9/20
 * Modified: 2018/9/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import java.util.List;

import com.istockage.model.entity.SecuritiesEntity;

/**
 * securities service interface
 * 
 * @author 詹晟
 */
public interface SecuritiesService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.SecuritiesServiceImpl#selectBySe_no(String)
	 */
	SecuritiesEntity selectBySe_no(String se_no);

	/**
	 * @see com.istockage.model.service.impl.SecuritiesServiceImpl#selectLikeBySe_noOrSe_name(String)
	 */
	List<SecuritiesEntity> selectLikeBySe_noOrSe_name(String search);

}
