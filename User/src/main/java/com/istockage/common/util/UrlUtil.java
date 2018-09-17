package com.istockage.common.util;

public class UrlUtil {

	public static String getPath(String servletPath) {

		return servletPath.substring(1, servletPath.length());
	}

	public static String getRequestPath(String servletPath, String queryString) {

		String path = getPath(servletPath);

		if (queryString != null) {
			return path + "?" + queryString;
		} else {
			return path;
		}
	}

}
