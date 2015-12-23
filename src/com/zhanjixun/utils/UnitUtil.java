package com.zhanjixun.utils;

import java.text.NumberFormat;
import java.util.Locale;

import android.content.Context;

/**
 * 格式转换和单位转换的工具
 * 
 * @author 詹命天子
 *
 */
public class UnitUtil {
	/**
	 * 将double转换成¥
	 * 
	 * @param d
	 * @return
	 */
	public static String toRMB(double d) {
		return NumberFormat.getCurrencyInstance(Locale.CHINA).format(d);
	}

	public static String toRMB(String s) {
		return NumberFormat.getCurrencyInstance(Locale.CHINA).format(
				Double.parseDouble(s));
	}

	/**
	 * 保留小数位
	 * 
	 * @param f
	 * @param digits
	 * @return
	 */
	public static String NumberFormat(float f, int digits) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(digits);
		return format.format(f);
	}

	/**
	 * dip转像素
	 * 
	 * @param context
	 * @param dip
	 * @return
	 */
	public static int DipToPixels(Context context, float dip) {
		return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
	}

	/**
	 * 像素转dip
	 * 
	 * @param context
	 * @param Pixels
	 * @return
	 */
	public static float PixelsToDip(Context context, int Pixels) {
		return Pixels / context.getResources().getDisplayMetrics().density;
	}
}
