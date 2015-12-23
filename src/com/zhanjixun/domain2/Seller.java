package com.zhanjixun.domain2;

import java.util.List;

public class Seller {
	public static final int TYPE_FARMER = 1;
	public static final int TYPE_FISHER = 0;
	private String shopId;
	private String shopName;
	private String grade;
	private String shopPhoto;
	private Float sendPrice;
	private List<String> getTypeString;
	private Double longitude;
	private Double latitude;
	private Integer shopType;// 0表示渔户，1是养殖户

	/**
	 * @return shopId
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * @param shopId
	 *            要设置的 shopId
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * @return shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @param shopName
	 *            要设置的 shopName
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * @return grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            要设置的 grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return shopPhoto
	 */
	public String getShopPhoto() {
		return shopPhoto;
	}

	/**
	 * @param shopPhoto
	 *            要设置的 shopPhoto
	 */
	public void setShopPhoto(String shopPhoto) {
		this.shopPhoto = shopPhoto;
	}

	/**
	 * @return sendPrice
	 */
	public Float getSendPrice() {
		return sendPrice;
	}

	/**
	 * @param sendPrice
	 *            要设置的 sendPrice
	 */
	public void setSendPrice(Float sendPrice) {
		this.sendPrice = sendPrice;
	}

	/**
	 * @return getTypeString
	 */
	public List<String> getGetTypeString() {
		return getTypeString;
	}

	/**
	 * @param getTypeString
	 *            要设置的 getTypeString
	 */
	public void setGetTypeString(List<String> getTypeString) {
		this.getTypeString = getTypeString;
	}

	/**
	 * @return longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            要设置的 longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            要设置的 latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return shopType
	 */
	public Integer getShopType() {
		return shopType;
	}

	/**
	 * @param shopType
	 *            要设置的 shopType
	 */
	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

}
