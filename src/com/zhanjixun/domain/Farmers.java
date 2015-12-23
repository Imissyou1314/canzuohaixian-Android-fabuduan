package com.zhanjixun.domain;

import java.util.List;

/**
 * 养殖户
 * 
 * @author 詹命天子
 *
 */
public class Farmers extends Seller {
	/**
	 * 捕捞方式
	 */
	private List<String> getTypes;
	/**
	 * 距离
	 */
	private long distance;
	/**
	 * 地址
	 */
	private String address;

	/**
	 * @return getTypes
	 */
	public List<String> getGetTypes() {
		return getTypes;
	}

	/**
	 * @return distance
	 */
	public long getDistance() {
		return distance;
	}

	/**
	 * @param distance 要设置的 distance
	 */
	public void setDistance(long distance) {
		this.distance = distance;
	}

	/**
	 * @param getTypes
	 *            要设置的 getTypes
	 */
	public void setGetTypes(List<String> getTypes) {
		this.getTypes = getTypes;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            要设置的 address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
