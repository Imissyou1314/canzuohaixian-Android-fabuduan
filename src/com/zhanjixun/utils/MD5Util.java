package com.zhanjixun.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String MD5_16bit(String origin) {
		return MD5_32bit(origin).substring(8, 24);
	}

	public static String MD5_32bit(String origin) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(origin.getBytes());

			StringBuffer buf = new StringBuffer();
			for (int offset = 0; offset < digest.length; offset++) {

				int i = digest[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

}
