package com.istockage.common.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BindingResultUtil {

	public static String getFieldErrors(BindingResult bindingResult) {

		List<FieldError> list = bindingResult.getFieldErrors();

		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				stringBuilder.append(list.get(i).getField());
			}
			if (i > 0) {
				stringBuilder.append(", ");
				stringBuilder.append(list.get(i).getField());
			}
		}

		return stringBuilder.toString();
	}

}
