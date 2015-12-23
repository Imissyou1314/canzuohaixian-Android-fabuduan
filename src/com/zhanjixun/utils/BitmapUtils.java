package com.zhanjixun.utils;

import android.graphics.Bitmap;

public class BitmapUtils {

	/**
	 * 按指定宽高截取图片
	 * 
	 * @param bitmap
	 * @param width
	 */
	public static Bitmap getBitmap(Bitmap bitmap, int width, int height) {
		double scale1 = width * 1.0 / height;
		double scale2 = bitmap.getWidth() * 1.0 / bitmap.getHeight();
		int x = 0;
		int y = 0;
		if (scale1 > scale2) {
			height = (int) (height * (bitmap.getWidth() * 1.0 / width));
			y = (bitmap.getHeight() - height) / 2;
			width = bitmap.getWidth();
		} else if (scale1 < scale2) {
			width = (int) (width * (bitmap.getHeight() * 1.0 / height));
			x = (bitmap.getWidth() - width) / 2;
			height = bitmap.getHeight();
		} else {
			return bitmap;
		}

		// int x=0, y=0, swidth=bitmap.getWidth(), sheight=bitmap.getHeight();
		// if(width<bitmap.getWidth()){
		// x = (bitmap.getWidth()-width)/2;
		// swidth = width;
		// }
		// if(height<bitmap.getHeight()){
		// y = (bitmap.getHeight()-height)/2;
		// sheight = height;
		// }
		Bitmap result = Bitmap.createBitmap(bitmap, x, y, width, height);
		return result;
	}
}
