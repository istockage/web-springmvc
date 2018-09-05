package com.istockage.common.editor;

import java.beans.PropertyEditorSupport;

import com.istockage.model.entity.SecuritiesAccountEntity;

public class SecuritiesAccountEntityPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (!"0".equals(text)) {
			SecuritiesAccountEntity securitiesAccountEntity = new SecuritiesAccountEntity();
			securitiesAccountEntity.setSa_id(Integer.parseInt(text));
			setValue(securitiesAccountEntity);
		}
	}

}
