package com.istockage.common.query;

public interface HqlQuery {

	// path_category
	/** extension 搜尋 */
	public static final String HQL_SELECT_PATH_CATEGORY_BY_EXTENSION = "from PathCategoryEntity where pc_extension=:pc_extension";

}
