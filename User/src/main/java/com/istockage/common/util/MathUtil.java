package com.istockage.common.util;

public class MathUtil {

	public static String getMe_no() {

		return String.format("%010d", (long) (Math.random() * 10000000000L));
	}

	public static String getMe_password_random() {

		return String.format("%06d", (int) (Math.random() * 1000000));
	}

}
