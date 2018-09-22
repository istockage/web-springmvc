package com.istockage.common.editor;

import java.beans.PropertyEditorSupport;

import com.istockage.model.entity.SecuritiesBrokerHeadEntity;

public class SecuritiesBrokerHeadEntityPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (!"0".equals(text)) {
			SecuritiesBrokerHeadEntity securitiesBrokerHeadEntity = new SecuritiesBrokerHeadEntity();
			securitiesBrokerHeadEntity.setSh_no(text);
			setValue(securitiesBrokerHeadEntity);
		}
	}

}
