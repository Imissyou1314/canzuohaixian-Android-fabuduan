package com.zhanjixun.interfaces;

import java.util.Map;

public interface OnDataReturnListener {
	public abstract void onDataReturn(String taskTag, Map<String, Object> result);
}
