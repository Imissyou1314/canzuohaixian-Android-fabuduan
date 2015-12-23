package com.zhanjixun.domain2;

import java.util.Date;
import java.util.List;

public class Order {
	public final static int state_finish = 4;
	public final static int state_un_commet = 3;
	public final static int state_un_get = 2;
	public final static int state_un_pay = 1;
	public final static int state_un_sent = 0;
	public final static String[] STATE_STR = { "提醒发货", "去付款", "查看物流", "去评论",
			"已完成" };
	private Date createTime;
	private List<Good> ordersDetail;
	private String ordersId;
	private String phone;
	private Double postagePrice;
	private String sendAddress;
	private String shopId;
	private String shopKeeperName;
	private String shopPhoto;
	private Integer state;
	private Double totalprice;
	private String userName;

	/**
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @return ordersDetail
	 */
	public List<Good> getOrdersDetail() {
		return ordersDetail;
	}

	/**
	 * @return ordersId
	 */
	public String getOrdersId() {
		return ordersId;
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return postagePrice
	 */
	public Double getPostagePrice() {
		return postagePrice;
	}

	/**
	 * @return sendAddress
	 */
	public String getSendAddress() {
		return sendAddress;
	}

	/**
	 * @return shopId
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * @return shopKeeperName
	 */
	public String getShopKeeperName() {
		return shopKeeperName;
	}

	/**
	 * @return shopPhoto
	 */
	public String getShopPhoto() {
		return shopPhoto;
	}

	/**
	 * @return state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @return totalprice
	 */
	public Double getTotalprice() {
		return totalprice;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param createTime
	 *            要设置的 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @param ordersDetail
	 *            要设置的 ordersDetail
	 */
	public void setOrdersDetail(List<Good> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}

	/**
	 * @param ordersId
	 *            要设置的 ordersId
	 */
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	/**
	 * @param phone
	 *            要设置的 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param postagePrice
	 *            要设置的 postagePrice
	 */
	public void setPostagePrice(Double postagePrice) {
		this.postagePrice = postagePrice;
	}

	/**
	 * @param sendAddress
	 *            要设置的 sendAddress
	 */
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	/**
	 * @param shopId
	 *            要设置的 shopId
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * @param shopKeeperName
	 *            要设置的 shopKeeperName
	 */
	public void setShopKeeperName(String shopKeeperName) {
		this.shopKeeperName = shopKeeperName;
	}

	/**
	 * @param shopPhoto
	 *            要设置的 shopPhoto
	 */
	public void setShopPhoto(String shopPhoto) {
		this.shopPhoto = shopPhoto;
	}

	/**
	 * @param state
	 *            要设置的 state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @param totalprice
	 *            要设置的 totalprice
	 */
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	/**
	 * @param userName
	 *            要设置的 userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
