package com.istockage.common.editor;

import java.beans.PropertyEditorSupport;

import com.istockage.model.entity.SecuritiesBrokerBranchId;

public class SecuritiesBrokerBranchIdPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (!"0".equals(text)) {
			SecuritiesBrokerBranchId securitiesBrokerBranchId = new SecuritiesBrokerBranchId();
			securitiesBrokerBranchId.setSb_no(text);
			setValue(securitiesBrokerBranchId);
		}
	}

}
