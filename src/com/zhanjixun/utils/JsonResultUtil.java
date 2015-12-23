package com.zhanjixun.utils;

import java.util.Map;

import com.zhanjixun.data.Constants;

public class JsonResultUtil {
	public static boolean state(Map<String, Object> result) {
		return result.get(Constants.JSON_STATE).equals("true");
	}

	public static String message(Map<String, Object> result) {
		return result.get(Constants.JSON_MESSAGE) + "";
	}
}
