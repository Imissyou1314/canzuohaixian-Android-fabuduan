package com.zhanjixun.data;

import android.os.Environment;

import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.domain2.Location;
import com.zhanjixun.domain2.Seller;
import com.zhanjixun.domain2.User;

public class Constants {

	public static final String HOST = "http://www.earltech.cn:8080";

	// public static final String HOST = "http://192.168.1.111:8080";

	public static final String JSON_STATE = "serviceResult";
	public static final String JSON_MESSAGE = "resultInfo";

	public static final String XML_USER = "XML_USER";

	public static final String XML_CAR = "XML_CAR";

	public static final String CALL_PHONE = "13763012723";

	public static Location location;

	public static String HOME_DIR = Environment.getExternalStorageDirectory()
			+ "/fishshop";

	public static String CACHE_DIR = HOME_DIR + "/Cache";

	public static User user;

	public static Seller seller;

	public static final BaseResult SERVER_ERROR = new BaseResult() {
		{
			setServiceResult(false);
			setResultInfo("服务器连接错误！");
		}
	};
	public static final BaseResult JSON_ERROR = new BaseResult() {
		{
			setServiceResult(false);
			setResultInfo("JSON数据解析错误！");
		}
	};
}
