package com.istockage.common.editor;

import java.beans.PropertyEditorSupport;

import com.istockage.model.entity.SecuritiesBrokerBranchEntity;

public class SecuritiesBrokerBranchEntityPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (!"0".equals(text)) {
			SecuritiesBrokerBranchEntity securitiesBrokerBranchEntity = new SecuritiesBrokerBranchEntity();
			securitiesBrokerBranchEntity.setSb_id(Integer.parseInt(text));
			setValue(securitiesBrokerBranchEntity);
		}
	}

}
