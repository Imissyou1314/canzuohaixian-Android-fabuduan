package com.zhanjixun.domain2;

import java.util.Date;

public class Comment {
	public static final int TYPE_GOOD = 1;
	public static final int TYPE_BAD = -1;
	public static final int TYPE_MID = 0;

	private Integer commentType;
	private String content;
	private String phoneNumber;
	private Date createTime;
	private String username;
	private String shopName;

	/**
	 * @return commentType
	 */
	public Integer getCommentType() {
		return commentType;
	}

	/**
	 * @param commentType
	 *            要设置的 commentType
	 */
	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            要设置的 content
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            要设置的 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            要设置的 username
	 */
	public void setUsername(String username) {
		this.username = username;
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
}
