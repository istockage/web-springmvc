package com.istockage.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class CodeId implements Serializable {

	private static final long serialVersionUID = 1L;

	private CodeCategoryEntity co_CodeCategoryEntity;
	private Byte co_no;

	public CodeId() {

	}

	public CodeId(CodeCategoryEntity co_CodeCategoryEntity, Byte co_no) {
		this.co_CodeCategoryEntity = co_CodeCategoryEntity;
		this.co_no = co_no;
	}

	public CodeCategoryEntity getCo_CodeCategoryEntity() {
		return co_CodeCategoryEntity;
	}

	public void setCo_CodeCategoryEntity(CodeCategoryEntity co_CodeCategoryEntity) {
		this.co_CodeCategoryEntity = co_CodeCategoryEntity;
	}

	public Byte getCo_no() {
		return co_no;
	}

	public void setCo_no(Byte co_no) {
		this.co_no = co_no;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CodeId)) {
			return false;
		}
		CodeId that = (CodeId) obj;
		return Objects.equals(getCo_CodeCategoryEntity(), that.getCo_CodeCategoryEntity())
				&& Objects.equals(getCo_no(), that.getCo_no());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCo_CodeCategoryEntity(), getCo_no());
	}

}
