package com.zhanjixun.data;

import java.util.Map;

import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.net.AsyncHttpTask;

public class DataCenter {
	public void getDatasFromServer(String taskTag, String url,
			Map<String, String> params, OnDataReturnListener dataReturnListener) {
		new AsyncHttpTask(taskTag, dataReturnListener).execute(url, params);

	}
}
