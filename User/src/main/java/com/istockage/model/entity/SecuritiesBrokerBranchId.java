package com.istockage.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;

@Embeddable
public class SecuritiesBrokerBranchId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "sb_sh_no")
	private SecuritiesBrokerHeadEntity sb_SecuritiesBrokerHeadEntity;
	@Expose
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

}
