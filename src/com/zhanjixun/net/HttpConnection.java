package com.zhanjixun.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.zhanjixun.utils.HttpUtils;
import com.zhanjixun.utils.LogUtils;

public class HttpConnection {

	private static HttpURLConnection getDefultHttpURLConnection(String url,
			String method) throws MalformedURLException, IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setRequestMethod(method);
		connection.setRequestProperty("Accept-Language", "zh-CN");
		connection.setRequestProperty("Charset", "UTF-8, deflate");
		connection.setRequestProperty("accept",
				"text/html, application/xhtml+xml, */*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		connection.setReadTimeout(8 * 1000);
		connection.setConnectTimeout(8 * 1000);
		return connection;
	}

	public static String doGETMethod(String url, Map<String, String> parames)
			throws MalformedURLException, IOException {

		String GETMethodURL = HttpUtils.getGETMethodURL(url, parames);
		String result = null;
		LogUtils.v(GETMethodURL);
		HttpURLConnection connection = getDefultHttpURLConnection(GETMethodURL,
				"GET");
		connection.connect();
		if (connection.getResponseCode() == 200) {
			result = HttpUtils.getResponseAsString(connection.getInputStream());
			LogUtils.v(result);
		} else {
			LogUtils.v("«Î«Û ß∞‹£∫" + connection.getResponseCode());
		}
		return result;
	}

	public static String doPOSTMethod(String url, Map<String, String> parames)
			throws MalformedURLException, IOException {
		String result = null;
		LogUtils.v(url);
		HttpURLConnection connection = getDefultHttpURLConnection(url, "POST");
		connection.setDoOutput(true);
		connection.getOutputStream().write(
				HttpUtils.getPOSTMethodParamesAsBytes(parames));
		connection.connect();
		if (connection.getResponseCode() == 200) {
			result = HttpUtils.getResponseAsString(connection.getInputStream());
			LogUtils.v(result);
		} else {
			LogUtils.v("«Î«Û ß∞‹£∫" + connection.getResponseCode());
		}
		return result;
	}

}
