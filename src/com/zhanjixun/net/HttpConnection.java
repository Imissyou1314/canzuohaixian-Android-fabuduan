package com.zhanjixun.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

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
		connection.setReadTimeout(5 * 1000);
		connection.setConnectTimeout(5 * 1000);
		return connection;
	}

	public static String doGETMethod(String url, Map<String, String> parames)
			throws MalformedURLException, IOException {

		String GETMethodURL = HttpUtils.getGETMethodQueryString(url, parames);
		String result = null;
		LogUtils.v(GETMethodURL);
		HttpURLConnection connection = getDefultHttpURLConnection(GETMethodURL,
				"GET");
		connection.connect();
		if (connection.getResponseCode() == 200) {
			result = HttpUtils.getResponseAsString(connection.getInputStream());
			LogUtils.v(result);
		} else {
			LogUtils.v("请求失败：" + connection.getResponseCode());
		}
		return result;
	}

	public static String doPOSTMethod(String url, Map<String, String> parames)
			throws MalformedURLException, IOException {
		String result = null;
		HttpURLConnection connection = getDefultHttpURLConnection(url, "POST");
		connection.setDoOutput(true);
		if (parames != null) {
			connection.getOutputStream().write(
					HttpUtils.getPOSTMethodParamesAsBytes(parames));
		}
		connection.connect();
		int responseCode = connection.getResponseCode();
		LogUtils.d(responseCode + "");
		if (responseCode == 200) {
			result = HttpUtils.getResponseAsString(connection.getInputStream(),
					"UTF-8");
			LogUtils.v(url);
			LogUtils.v(parames != null ? parames.toString() : "");
			LogUtils.v(result);
		} else {
			LogUtils.v("请求失败：" + responseCode);
		}
		return result;
	}

	/**
	 * 网络中获取文件
	 * 
	 * @param url
	 * @param path
	 * @return
	 */
	public static boolean sendGetToFile(String url, String path) {
		HttpURLConnection httpURLConnection;
		FileOutputStream out = null;
		InputStream in = null;
		try {
			httpURLConnection = getDefultHttpURLConnection(url, "GET");
			httpURLConnection.connect();
			File file = new File(path);
			int responseCode = httpURLConnection.getResponseCode();
			if (200 == responseCode) {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				out = new FileOutputStream(file);
				in = httpURLConnection.getInputStream();
				int i = 0;
				byte[] buffer = new byte[1024];
				while ((i = in.read(buffer)) != -1) {
					out.write(buffer, 0, i);
				}
				return true;
			} else {
				// 请求失败
				LogUtils.v(responseCode + "");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.v("1=", "error");
			return false;
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Bitmap sendGetToBitmap(String url) throws Exception {

		HttpURLConnection httpURLConnection;
		FileOutputStream out = null;
		InputStream in = null;
		try {
			httpURLConnection = getDefultHttpURLConnection(url, "GET");
			httpURLConnection.connect();
			if (200 == httpURLConnection.getResponseCode()) {
				in = httpURLConnection.getInputStream();
				return BitmapFactory.decodeStream(in);
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception(e);
			}
		}
	}
}
