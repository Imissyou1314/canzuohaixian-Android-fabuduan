package com.zhanjixun.domain2;

public class Location {
	private double longitude;//
	private double latitude;// 纬度
	private String locationStr;

	/**
	 * @return longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            要设置的 longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            要设置的 latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return locationStr
	 */
	public String getLocationStr() {
		return locationStr;
	}

	/**
	 * @param locationStr
	 *            要设置的 locationStr
	 */
	public void setLocationStr(String locationStr) {
		this.locationStr = locationStr;
	}
}
