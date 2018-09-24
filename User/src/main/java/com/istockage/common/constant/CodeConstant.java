package com.istockage.common.constant;

public interface CodeConstant {

	// member
	public static final byte MEMBER_ACTIVITY_CLOSE = 0;

	public static final byte MEMBER_ACTIVITY_OPEN = 1;

	// stock
	/** 買賣類別 */
	public static final int STOCK_TYPE_CODE_CATEGORY = 1;

	/** 現股 */
	public static final byte SPOT_SHARE_CODE = 1;

	/** 融資 */
	public static final byte MARGIN_PURCHASE_CODE = 2;

	/** 融券 */
	public static final byte SHORT_SALE_CODE = 3;

	/** 中籤 */
	public static final byte DRAWING_LOTS_CODE = 4;

}
