package com.zhanjixun.utils;

import android.util.Log;

public class LogUtils {

	public static void i(String msg) {
		String tag = new Throwable().getStackTrace()[1].getClassName() + "."
				+ new Throwable().getStackTrace()[1].getMethodName() + "()";
		Log.i(tag, msg);
	}

	public static void d(String msg) {
		String tag = new Throwable().getStackTrace()[1].getClassName() + "."
				+ new Throwable().getStackTrace()[1].getMethodName() + "()";
		Log.d(tag, msg);
	}

	public static void w(String msg) {
		String tag = new Throwable().getStackTrace()[1].getClassName() + "."
				+ new Throwable().getStackTrace()[1].getMethodName() + "()";
		Log.w(tag, msg);
	}

	public static void e(String msg) {
		String tag = new Throwable().getStackTrace()[1].getClassName() + "."
				+ new Throwable().getStackTrace()[1].getMethodName() + "()";
		Log.e(tag, msg);
	}

	public static void v(String msg) {
		String tag = new Throwable().getStackTrace()[1].getClassName() + "."
				+ new Throwable().getStackTrace()[1].getMethodName() + "()";
		Log.v(tag, msg);
	}
}
