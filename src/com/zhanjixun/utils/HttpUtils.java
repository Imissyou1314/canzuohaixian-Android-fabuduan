package com.zhanjixun.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtils {

	public static String getResponseAsString(InputStream in, String charset)
			throws IOException {
		StringBuffer result = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,
				charset));
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str);
		}
		reader.close();
		return result.toString();
	}

	public static String getResponseAsString(InputStream in) throws IOException {
		StringBuffer result = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str);
		}
		reader.close();
		return result.toString();
	}

	public static String getGETMethodQueryString(String url,
			Map<String, String> parames) throws UnsupportedEncodingException {
		StringBuffer getUrl = new StringBuffer(url);
		if (parames != null && !parames.isEmpty()) {
			getUrl.append("?");
			for (Map.Entry<String, String> parm : parames.entrySet()) {
				getUrl.append(parm.getKey()).append("=")
						.append(URLEncoder.encode(parm.getValue(), "Utf-8"));
				getUrl.append("&");
			}
			getUrl.deleteCharAt(getUrl.length() - 1);
		}
		return getUrl.toString();
	}

	public static byte[] getPOSTMethodParamesAsBytes(Map<String, String> parames)
			throws UnsupportedEncodingException {
		StringBuffer result = new StringBuffer();
		if (parames != null && !parames.isEmpty()) {
			for (Map.Entry<String, String> parm : parames.entrySet()) {
				result.append(parm.getKey()).append("=")
						.append(URLEncoder.encode(parm.getValue(), "UTF-8"));
				result.append("&");
			}
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString().getBytes();
	}
}
