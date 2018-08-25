package com.istockage.common.query;

public interface HqlQuery {

	// path_category
	/** extension 搜尋 */
	public static final String HQL_SELECT_PATH_CATEGORY_BY_EXTENSION = "from PathCategoryEntity where pc_extension=:pc_extension";

	// securities_broker_head
	/** 搜尋所有證券商 */
	public static final String HQL_SELECT_SECURITIES_BROKER_HEAD = "from SecuritiesBrokerHeadEntity order by sh_id";

	// securities_broker_branch
	/** 證券商流水號搜尋 */
	public static final String HQL_SELECT_SECURITIES_BROKER_BRANCH_BY_SECURITIES_BROKER_HEAD = "from SecuritiesBrokerBranchEntity where sb_sh_id=:sb_sh_id order by sb_id";

	// securities_account
	/** 會員流水號搜尋 */
	public static final String HQL_SELECT_SECURITIES_ACCOUNT_BY_MEMBER = "from SecuritiesAccountEntity where sa_me_id=:sa_me_id order by sa_id asc";

}
