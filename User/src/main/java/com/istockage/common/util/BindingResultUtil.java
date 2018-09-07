package com.istockage.common.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BindingResultUtil {

	public static String getFieldErrors(BindingResult bindingResult) {

		List<FieldError> list = bindingResult.getFieldErrors();

		String fieldErrors = "";

		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				fieldErrors = list.get(i).getField();
			}
			if (i > 0) {
				fieldErrors = fieldErrors.concat(", ").concat(list.get(i).getField());
			}
		}

		return fieldErrors;
	}

}
