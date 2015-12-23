package com.zhanjixun.data;

import java.util.Hashtable;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.zhanjixun.net.HttpConnection;
import com.zhanjixun.utils.Blur;
import com.zhanjixun.utils.FileUtil;
import com.zhanjixun.utils.StringUtil;

public class LoadImage {
	public static final String BLUR_KEY = "glass";
	private LruCache<String, Bitmap> lruCache;
	private static LoadImage loadImage;
	private AsynNetLoadBitmap asynNetLoadBitmap;
	private AsynDiskLoadBitmap asynDiskLoadBitmap;
	private Hashtable<String, ImageView> imageViews;

	/**
	 * 单例
	 */
	private LoadImage() {
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int mCacheSize = maxMemory / 8;
		lruCache = new LruCache<String, Bitmap>(mCacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	public static LoadImage getInstance() {
		if (null == loadImage) {
			synchronized (LoadImage.class) {
				if (null == loadImage) {
					return new LoadImage();
				}
			}
		}
		return loadImage;
	}

	/**
	 * 从内存缓存区获取bitmap
	 * 
	 * @param path
	 * @return
	 */
	public Bitmap getBitmapFromLruCache(String path) {
		return lruCache.get(StringUtil.MD5(path));
	}

	/**
	 * 从内存，sd卡或网络获取bitmap
	 * 
	 * @param path
	 * @return
	 */
	public Bitmap getBitmap(String path) {
		String key = StringUtil.MD5(path);
		Bitmap bitmap = lruCache.get(key);
		if (null != bitmap) {
			return bitmap;
		}
		if (path.contains("http:")) {
			String filePath = Constants.CACHE_DIR + "/" + key;
			bitmap = FileUtil.loadBitmap(filePath);
			if (null != bitmap) {
				putImage(key, bitmap);// 放入系统缓存中
				return bitmap;
			} else {
				if (HttpConnection.sendGetToFile(path, filePath)) {
					bitmap = FileUtil.loadBitmap(path);
					putImage(key, bitmap);
					return bitmap;
				}
			}
		} else {
			bitmap = FileUtil.loadBitmap(path);
			putImage(key, bitmap);
			return bitmap;
		}
		return null;
	}

	/**
	 * 获取毛玻璃图片
	 * 
	 * @param path
	 * @param context
	 * @return
	 */
	public Bitmap getBlurBitmap(String path, Context context) {
		// 毛玻璃图片md5
		String urlMD5KeyGlass = StringUtil.MD5(path + BLUR_KEY);
		// 原图md5
		String urlMD5Key = StringUtil.MD5(path);

		String glassPath = Constants.CACHE_DIR + "/" + urlMD5KeyGlass;
		String cachePath = Constants.CACHE_DIR + "/" + urlMD5Key;
		// 先从LruCache中获取图片
		Bitmap bitmap = lruCache.get(urlMD5KeyGlass);
		if (null != bitmap) {
			return bitmap;
		}
		// 根据毛玻璃图片地址在Cache中找图片
		Bitmap glassBitmap = FileUtil.loadBitmap(glassPath);
		if (glassBitmap != null) {
			return glassBitmap;
		} else {
			// 在Cache中找原图
			Bitmap cacheBitmap = FileUtil.loadBitmap(cachePath);
			if (cacheBitmap != null) {
				Bitmap blurBitmap = Blur.fastblur(context, cacheBitmap, 12);
				// 将毛玻璃的图片存入LruCache
				putImage(glassPath, blurBitmap);
				// 将毛玻璃图片存入Cache
				FileUtil.storeBitmap(glassPath, blurBitmap);
				return blurBitmap;
			} else {
				if (HttpConnection.sendGetToFile(path, cachePath)) {

					Bitmap netBitmap = FileUtil.loadBitmap(path);
					Bitmap netBlurBitmap = Blur
							.fastblur(context, netBitmap, 12);
					putImage(glassPath, netBlurBitmap);
					FileUtil.storeBitmap(glassPath, netBlurBitmap);
					return netBlurBitmap;
				}
			}
		}
		return null;
	}

	public void setImage(String path, ImageView imageView) {
		if (StringUtil.isEmptyString(path))
			return;
		if (imageView == null)
			return;
		String key = StringUtil.MD5(path);
		Bitmap bitmap = lruCache.get(key);
		if (null != bitmap) {
			imageView.setImageBitmap(bitmap);
			return;
		}
		if (-1 != path.indexOf("http:")) {
			String filePath = StringUtil
					.toMDFileName(Constants.CACHE_DIR, path);

			if (FileUtil.fileIsExists(filePath)) {
				imageViews.put(StringUtil.MD5(filePath), imageView);
				asynDiskLoadBitmap.execute(filePath);
			} else {
				imageViews.put(StringUtil.MD5(path), imageView);
				asynNetLoadBitmap.execute(path);
			}
		} else {
			imageViews.put(StringUtil.MD5(path), imageView);
			asynDiskLoadBitmap.execute(path);
		}
	}

	class AsynNetLoadBitmap extends AsyncTask<String, Integer, Bitmap> {

		private String key;

		@Override
		protected Bitmap doInBackground(String... params) {
			String urlStr = params[0];
			key = StringUtil.MD5(urlStr);
			try {
				return HttpConnection.sendGetToBitmap(urlStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			imageViews.get(key).setImageBitmap(result);
		}
	}

	class AsynDiskLoadBitmap extends AsyncTask<String, Integer, Bitmap> {

		String key;

		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0];
			return FileUtil.loadBitmap(path);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
		}
	}

	private void putImage(String key, Bitmap bitmap) {
		if (StringUtil.isEmptyString(key))
			return;
		if (null == bitmap)
			return;
		lruCache.put(key, bitmap);
	}

}
