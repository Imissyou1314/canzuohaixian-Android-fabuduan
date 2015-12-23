package com.zhanjixun.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtil {

	public static String getString(Context context, String name, String key,
			String defValue) {
		SharedPreferences sp = context.getSharedPreferences(name,
				Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}

	public static boolean saveString(Context context, String name, String key,
			String value) {
		SharedPreferences sp = context.getSharedPreferences(name,
				Context.MODE_PRIVATE);

		Editor edit = sp.edit();
		edit.putString(key, value);
		return edit.commit();
	}

	public static boolean deleteSP(Context context, String name) {
		SharedPreferences sp = context.getSharedPreferences(name,
				Context.MODE_PRIVATE);

		boolean delete = sp.edit().clear().commit();
		LogUtils.v("É¾³ýSharedPreferences:" + delete);
		return delete;
	}
}
