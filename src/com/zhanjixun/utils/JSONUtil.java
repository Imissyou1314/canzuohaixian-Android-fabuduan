package com.zhanjixun.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.text.TextUtils;

public class JSONUtil {
	public static Map<String, Object> getJosn(String jsonStr) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!TextUtils.isEmpty(jsonStr)) {
			JSONObject json = new JSONObject(jsonStr);
			Iterator<String> i = json.keys();
			while (i.hasNext()) {
				String key = (String) i.next();
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

	public static List<Map<String, Object>> getList(String jsonStr)
			throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONArray ja = new JSONArray(jsonStr);
		for (int j = 0; j < ja.length(); j++) {
			String jm = ja.get(j) + "";
			if (jm.indexOf("{") == 0) {
				Map<String, Object> m = getJosn(jm);
				list.add(m);
			}
		}
		return list;
	}
}
