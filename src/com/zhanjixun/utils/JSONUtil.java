package com.zhanjixun.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class JsonUtil {
	public static Map<String, Object> getJosn(String jsonStr)
			throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!TextUtils.isEmpty(jsonStr)) {
			JSONObject json = new JSONObject(jsonStr);
			Iterator<String> i = json.keys();
			while (i.hasNext()) {
				String key = i.next();
				String value = json.getString(key);
				if (value.indexOf("{") == 0) {
					map.put(key.trim(), getJosn(value));
				} else if (value.indexOf("[") == 0) {
					map.put(key.trim(), getList(value));
				} else {
					map.put(key.trim(), value);
				}
			}
		}
		return map;
	}

	public static List<Object> getList(String jsonStr) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		JSONArray ja = new JSONArray(jsonStr);
		for (int j = 0; j < ja.length(); j++) {
			String jm = ja.get(j) + "";
			try {
				new JSONObject(jm);
				list.add(getJosn(jm));
			} catch (JSONException e) {
				try {
					new JSONArray(jm);
					list.add(getList(jm));
				} catch (JSONException e2) {
					list.add(jm);
				}
			}

			// if (jm.startsWith("{")) {
			// list.add(getJosn(jm));
			// } else if (jm.startsWith("[")) {
			// list.add(getList(jm));
			// } else {
			// list.add(jm);
			// }
		}
		return list;
	}

}
