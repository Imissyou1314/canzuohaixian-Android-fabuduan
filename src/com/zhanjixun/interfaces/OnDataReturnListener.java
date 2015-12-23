package com.zhanjixun.interfaces;

import com.zhanjixun.domain2.BaseResult;

public interface OnDataReturnListener {
	public abstract void onDataReturn(String taskTag, BaseResult result,
			String json);
}
