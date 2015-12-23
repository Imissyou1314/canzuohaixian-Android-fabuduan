package com.zhanjixun.utils;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.util.Base64;

public class ImageUtil {
	public static String getImageBase64String(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] bytes = baos.toByteArray();
		byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
		return new String(encode);
	}
}
