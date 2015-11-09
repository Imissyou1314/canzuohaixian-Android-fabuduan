package com.zhanjixun.utils;

public class StringUtils {
	public static boolean isEmptyString(String string) {
		return string == null || string.equals("") || string.equals("null");
	}
}
