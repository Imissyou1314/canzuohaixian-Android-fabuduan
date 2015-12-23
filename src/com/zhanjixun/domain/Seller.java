package com.zhanjixun.domain;

import java.util.List;

public class Seller {
	/**
	 * id
	 */
	private String id;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 信用值
	 */
	private String grade;
	/**
	 * 起送价
	 */
	private double sendPrice;
	/**
	 * 头像URL
	 */
	private String headImage;
	
	private List<String> showImageUrls;

	/**
	 * @return headImage
	 */
	public String getHeadImage() {
		return headImage;
	}

	/**
	 * @param headImage 要设置的 headImage
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return sendPrice
	 */
	public double getSendPrice() {
		return sendPrice;
	}

	/**
	 * @param sendPrice
	 *            要设置的 sendPrice
	 */
	public void setSendPrice(double sendPrice) {
		this.sendPrice = sendPrice;
	}

	public List<String> getShowImageUrls() {
		return showImageUrls;
	}

	public void setShowImageUrls(List<String> showImageUrls) {
		this.showImageUrls = showImageUrls;
	}

	
}
