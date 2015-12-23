package com.zhanjixun.domain2;

import com.google.gson.Gson;
import com.zhanjixun.data.Constants;
import com.zhanjixun.utils.SPUtil;

import android.content.Context;

public class User {
	private String userId;
	private String phoneNumber;
	private String password;
	private String userName;
	private String headImage;

	public boolean saveUserInfo(Context context) {
		return SPUtil.saveString(context, Constants.XML_USER,
				Constants.XML_USER, new Gson().toJson(this));
	}

	public static User getUserFormSP(Context context) {
		return new Gson().fromJson(SPUtil.getString(context,
				Constants.XML_USER, Constants.XML_USER, "{}"),
				User.class);
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            要设置的 userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            要设置的 phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            要设置的 userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return headImage
	 */
	public String getHeadImage() {
		return headImage;
	}

	/**
	 * @param headImage
	 *            要设置的 headImage
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
}
