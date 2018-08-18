package com.istockage.controller;

import com.istockage.common.constant.CodeConstant;
import com.istockage.common.constant.CommonConstant;
import com.istockage.common.constant.ModelAttributeConstant;
import com.istockage.common.constant.PathConstant;
import com.istockage.common.message.ErrorMessage;
import com.istockage.common.message.SuccessMessage;

public interface ControllerConstant
		extends CodeConstant, CommonConstant, ModelAttributeConstant, PathConstant, ErrorMessage, SuccessMessage {

	public static final int PAGE_ROW_COUNT_NUMBER = 10;

	public static final int GROUP_ROW_COUNT_NUMBER = 5;

}
