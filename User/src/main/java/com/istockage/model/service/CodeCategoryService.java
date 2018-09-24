/*
 * iStockage
 * File: CodeCategoryService.java
 * Author: 詹晟
 * Created: 2018/9/6
 * Modified: 2018/9/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import com.istockage.model.entity.CodeCategoryEntity;

/**
 * code_category service interface
 * 
 * @author 詹晟
 */
public interface CodeCategoryService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.CodeCategoryServiceImpl#selectByCc_no(Integer)
	 */
	CodeCategoryEntity selectByCc_no(Integer cc_no);

}
