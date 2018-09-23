package com.istockage.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class SecuritiesBrokerBranchId implements Serializable {

	private static final long serialVersionUID = 1L;

	private SecuritiesBrokerHeadEntity sb_SecuritiesBrokerHeadEntity;
	private String sb_no;

	public SecuritiesBrokerHeadEntity getSb_SecuritiesBrokerHeadEntity() {
		return sb_SecuritiesBrokerHeadEntity;
	}

	public void setSb_SecuritiesBrokerHeadEntity(SecuritiesBrokerHeadEntity sb_SecuritiesBrokerHeadEntity) {
		this.sb_SecuritiesBrokerHeadEntity = sb_SecuritiesBrokerHeadEntity;
	}

	public String getSb_no() {
		return sb_no;
	}

	public void setSb_no(String sb_no) {
		this.sb_no = sb_no;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SecuritiesBrokerBranchId)) {
			return false;
		}
		SecuritiesBrokerBranchId that = (SecuritiesBrokerBranchId) obj;
		return Objects.equals(getSb_SecuritiesBrokerHeadEntity(), that.getSb_SecuritiesBrokerHeadEntity())
				&& Objects.equals(getSb_no(), that.getSb_no());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSb_SecuritiesBrokerHeadEntity(), getSb_no());
	}

}
