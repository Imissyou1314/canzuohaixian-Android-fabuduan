package com.zhanjixun.utils;

import android.util.Log;

public class LogUtils {

	private static String tag = getClassName() + "." + getMethodName();

	private static String getClassName() {
		return new Object() {
			public String getClassName() {
				String clazzName = this.getClass().getName();
				return clazzName.substring(0, clazzName.lastIndexOf('$'));
			}
		}.getClassName();
	}

	private static String getMethodName() {
		return new Throwable().getStackTrace()[1].getMethodName();
	}

	public static void i(String msg) {
		Log.i(tag, msg);
	}

	public static void d(String msg) {
		Log.d(tag, msg);
	}

	public static void w(String msg) {
		Log.w(tag, msg);
	}

	public static void e(String msg) {
		Log.e(tag, msg);
	}

	public static void v(String msg) {
		Log.v(tag, msg);
	}
}
