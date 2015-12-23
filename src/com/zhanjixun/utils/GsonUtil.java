package com.zhanjixun.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class GsonUtil {

	Map<String, Object> map = null;

	public GsonUtil() {
		map = new HashMap<String, Object>();
	}

	public GsonUtil(Map<String, Object> map) {
		this.map = map;
	}

	public void put(String key, Object value) {
		map.put(key, value);
	}

	@Override
	public String toString() {
		if (map != null && map.size() != 0) {
			return new Gson().toJson(map);
		}
		return "[]";
	}
}
