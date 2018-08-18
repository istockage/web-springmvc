package com.istockage.common.util;

import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {

	/** 每頁最大筆數 */
	public static final String PAGE_ROW_COUNT = "pageRowCount";

	/** 總頁數 */
	public static final String PAGE_COUNT = "pageCount";

	/** 當前頁碼 */
	public static final String CURRENT_PAGE = "currentPage";

	/** 每群最大頁數 */
	public static final String GROUP_ROW_COUNT = "groupRowCount";

	/** 總群數 */
	public static final String GROUP_COUNT = "groupCount";

	/** 當前群序 */
	public static final String CURRENT_GROUP = "currentGroup";

	/** 當前群序起始頁碼 */
	public static final String CURRENT_GROUP_BEGIN = "currentGroupBegin";

	/** 當前群序結束頁碼 */
	public static final String CURRENT_GROUP_END = "currentGroupEnd";

	/**
	 * 取得總頁數
	 * 
	 * @param totalRowCount int --> 總筆數
	 * @param pageRowCount int --> 每頁最大筆數
	 * @return int
	 */
	public static int getPageCount(int totalRowCount, int pageRowCount) {

		int pageCount = totalRowCount / pageRowCount;

		return (totalRowCount % pageRowCount == 0) ? pageCount : (pageCount + 1);
	}

	/**
	 * 取得總群數
	 * 
	 * @param pageCount int --> 總頁數
	 * @param groupRowCount int --> 每群最大頁數
	 * @return int
	 */
	public static int getGroupCount(int pageCount, int groupRowCount) {

		int groupCount = pageCount / groupRowCount;

		return (pageCount % groupRowCount == 0) ? groupCount : (groupCount + 1);
	}

	/**
	 * 取得當前群序
	 * 
	 * @param currentPage int --> 當前頁碼
	 * @param groupRowCount int --> 每群最大頁數
	 * @return int
	 */
	public static int getCurrentGroup(int currentPage, int groupRowCount) {

		int currentGroup = currentPage / groupRowCount;

		return (currentPage % groupRowCount == 0) ? currentGroup : (currentGroup + 1);
	}

	/**
	 * 取得當前群序起始頁碼
	 * 
	 * @param currentPage int --> 當前頁碼
	 * @param groupRowCount int --> 每群最大頁數
	 * @return int
	 */
	public static int getCurrentGroupBegin(int currentPage, int groupRowCount) {

		return ((getGroupCount(currentPage, groupRowCount) - 1) * groupRowCount) + 1;
	}

	/**
	 * 取得當前群序結束頁碼
	 * 
	 * @param pageCount int --> 總頁數
	 * @param currentPage int --> 當前頁碼
	 * @param groupRowCount int --> 每群最大頁數
	 * @return int
	 */
	public static int getCurrentGroupEnd(int pageCount, int currentPage, int groupRowCount) {

		int currentGroupBegin = getCurrentGroupBegin(currentPage, groupRowCount);

		return ((pageCount - currentGroupBegin) > (groupRowCount - 1)) ? (currentGroupBegin + (groupRowCount - 1))
				: pageCount;
	}

	/**
	 * 取得分頁資訊
	 * 
	 * @param pageRowCount int --> 每頁最大筆數
	 * @param pageCount int --> 總頁數
	 * @param currentPage int --> 當前頁碼
	 * @param groupRowCount int --> 每群最大頁數
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> allAttributes(int pageRowCount, int pageCount, int currentPage,
			int groupRowCount) {

		Map<String, Object> map = new HashMap<String, Object>();

		// 取得每頁最大筆數
		map.put(PAGE_ROW_COUNT, pageRowCount);

		// 取得總頁數
		map.put(PAGE_COUNT, pageCount);

		// 取得當前頁碼
		map.put(CURRENT_PAGE, currentPage);

		// 取得每群最大頁數
		map.put(GROUP_ROW_COUNT, groupRowCount);

		// 取得總群數
		map.put(GROUP_COUNT, getGroupCount(pageCount, groupRowCount));

		// 取得當前群序
		map.put(CURRENT_GROUP, getCurrentGroup(currentPage, groupRowCount));

		// 取得當前群序起始頁碼
		map.put(CURRENT_GROUP_BEGIN, getCurrentGroupBegin(currentPage, groupRowCount));

		// 取得當前群序結束頁碼
		map.put(CURRENT_GROUP_END, getCurrentGroupEnd(pageCount, currentPage, groupRowCount));

		return map;
	}

}
