package com.zhanjixun.net;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.zhanjixun.data.Constants;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.LogUtils;
import com.zhanjixun.utils.StringUtil;

public class AsyncHttpTask extends AsyncTask<Object, Intent, String> {

	public static final String POST = "POST";
	public static final String GET = "GET";

	private String taskTag;

	private OnDataReturnListener dataReturnListener;

	public AsyncHttpTask(String taskTag, OnDataReturnListener dataReturnListener) {
		super();
		this.taskTag = taskTag;
		this.dataReturnListener = dataReturnListener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Object... executeParams) {
		String url = (String) executeParams[0];
		String method = (String) executeParams[2];
		@SuppressWarnings("unchecked")
		Map<String, String> parames = (Map<String, String>) executeParams[1];
		try {
			if (method.equals(POST)) {
				return HttpConnection.doPOSTMethod(url, parames);
			} else {
				return HttpConnection.doGETMethod(url, parames);
			}

		} catch (Exception e) {
			LogUtils.e(e.toString());
		}
		return "";
	}

	@Override
	protected void onPostExecute(String json) {
		super.onPostExecute(json);

		BaseResult jsonResult = null;
		if (!StringUtil.isEmptyString(json)) {
			try {
				jsonResult = new Gson().fromJson(json, BaseResult.class);
			} catch (Exception e) {
				jsonResult = Constants.JSON_ERROR;
				e.printStackTrace();
			}
		} else {
			jsonResult = Constants.SERVER_ERROR;
		}
		try {
			JSONObject jo = new JSONObject(json).getJSONObject("resultParm");
			Map<String, String> map = new HashMap<String, String>();
			Iterator<String> keys = jo.keys();
			
			while (keys.hasNext()) {
				String k = keys.next();
				map.put(k, jo.getString(k));
			}
			jsonResult.setResultParam(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (dataReturnListener != null) {
			dataReturnListener.onDataReturn(taskTag, jsonResult, json);
		}
	}
}
