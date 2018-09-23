package com.istockage.common.editor;

import java.beans.PropertyEditorSupport;

import com.istockage.model.entity.SecuritiesBrokerBranchEntity;

public class SecuritiesBrokerBranchEntityPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (!"0".equals(text)) {
			SecuritiesBrokerBranchEntity securitiesBrokerBranchEntity = new SecuritiesBrokerBranchEntity();
			securitiesBrokerBranchEntity.getSecuritiesBrokerBranchId().setSb_no(text);
			setValue(securitiesBrokerBranchEntity);
		}
	}

}
