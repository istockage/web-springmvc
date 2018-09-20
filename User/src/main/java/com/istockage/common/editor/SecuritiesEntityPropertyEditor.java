package com.istockage.common.editor;

import java.beans.PropertyEditorSupport;

import com.istockage.model.entity.SecuritiesEntity;

public class SecuritiesEntityPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (text != null && !text.isEmpty()) {
			SecuritiesEntity securitiesEntity = new SecuritiesEntity();
			securitiesEntity.setSe_no(text);
			setValue(securitiesEntity);
		}
	}

}
