package com.zhanjixun.domain2;

import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.zhanjixun.data.Constants;
import com.zhanjixun.utils.SPUtil;

/**
 * 用于保存购物车信息和购物车提交订单的bean
 * 
 * @author 詹命天子
 *
 */
public class CarOrder {
	private float price;
	private String userId;
	private String shopId;
	private String buyerName;
	private String shopKeeperName;
	private String seaRecordId;
	private List<Good> ordersDetail;

	public static CarOrder getCarOrder(Context context) {
		String json = SPUtil.getString(context, Constants.XML_CAR,
				Constants.XML_CAR, "{}");
		CarOrder carOrder = new Gson().fromJson(json, CarOrder.class);
		return carOrder;
	}

	public static boolean saveCarOrder(CarOrder carOrder, Context context) {
		String json = new Gson().toJson(carOrder);
		return SPUtil.saveString(context, Constants.XML_CAR, Constants.XML_CAR,
				json);
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
	 * @return buyerName
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**
	 * @param buyerName
	 *            要设置的 buyerName
	 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	/**
	 * @return shopKeeperName
	 */
	public String getShopKeeperName() {
		return shopKeeperName;
	}

	/**
	 * @param shopKeeperName
	 *            要设置的 shopKeeperName
	 */
	public void setShopKeeperName(String shopKeeperName) {
		this.shopKeeperName = shopKeeperName;
	}

	/**
	 * @return seaRecordId
	 */
	public String getSeaRecordId() {
		return seaRecordId;
	}

	/**
	 * @param seaRecordId
	 *            要设置的 seaRecordId
	 */
	public void setSeaRecordId(String seaRecordId) {
		this.seaRecordId = seaRecordId;
	}

	/**
	 * @return ordersDetail
	 */
	public List<Good> getOrdersDetail() {
		return ordersDetail;
	}

	/**
	 * @param ordersDetail
	 *            要设置的 ordersDetail
	 */
	public void setOrdersDetail(List<Good> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}

	/**
	 * @return price
	 */
	public float getPrice() {
		price = 0;
		try {
			List<Good> ordersDetail = getOrdersDetail();
			for (Good g : ordersDetail) {
				price += g.getNumber() * g.getPrice();
			}
		} catch (Exception e) {
		}
		return price;
	}

	/**
	 * @param price
	 *            要设置的 price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
}
