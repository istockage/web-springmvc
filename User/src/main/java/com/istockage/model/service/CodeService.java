/*
 * iStockage
 * File: CodeService.java
 * Author: 詹晟
 * Created: 2018/9/24
 * Modified: 2018/9/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.istockage.model.service;

import com.istockage.model.entity.CodeCategoryEntity;
import com.istockage.model.entity.CodeEntity;

/**
 * code service interface
 * 
 * @author 詹晟
 */
public interface CodeService extends ServiceConstant {

	/**
	 * @see com.istockage.model.service.impl.CodeServiceImpl#selectByCodeId(CodeCategoryEntity,
	 *      Byte)
	 */
	CodeEntity selectByCodeId(CodeCategoryEntity co_CodeCategoryEntity, Byte co_no);

}
