/**
 * 
 */
package com.zhanjixun.domain;

import java.util.List;

/**
 * @author Imissyou
 * @Time  2015年11月25日
 *  订单详情页面
 */
public class Order_Information {
	
	//用户名
	private String user_name;
	//用户电话
	private String user_phome;
	//剩余天
	private String day;
	//剩余时
	private String hour;
	//收货地址
	private String Address;
    //物流状态
	private String order_state;
	//商店名
	private String order_ShopName;
	//所有商品的数量
	private String order_goodNumber;
	//所有商品的总商品价
	private String order_goodSumPirce;
	//所有商品物流价格
	private String order_goodLogisticsPrice;
	//所有商品item
	
	private List<Order_GoodItem> ordersDatil ;
	/**
	 * @return ordersDatil
	 */
	public List<Order_GoodItem> getOrdersDatil() {
		return ordersDatil;
	}
	/**
	 * @param ordersDatil 要设置的 ordersDatil
	 */
	public void setOrdersDatil(List<Order_GoodItem> ordersDatil) {
		this.ordersDatil = ordersDatil;
	}
	/**
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name 要设置的 user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return user_phome
	 */
	public String getUser_phome() {
		return user_phome;
	}
	/**
	 * @param user_phome 要设置的 user_phome
	 */
	public void setUser_phome(String user_phome) {
		this.user_phome = user_phome;
	}
	/**
	 * @return day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day 要设置的 day
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return hour
	 */
	public String getHour() {
		return hour;
	}
	/**
	 * @param hour 要设置的 hour
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	/**
	 * @return address
	 */
	public String getAddress() {
		return Address;
	}
	/**
	 * @param address 要设置的 address
	 */
	public void setAddress(String address) {
		Address = address;
	}
	/**
	 * @return order_goodNumber
	 */
	public String getOrder_goodNumber() {
		return order_goodNumber;
	}
	/**
	 * @param order_goodNumber 要设置的 order_goodNumber
	 */
	public void setOrder_goodNumber(String order_goodNumber) {
		this.order_goodNumber = order_goodNumber;
	}
	/**
	 * @return order_goodSumPirce
	 */
	public String getOrder_goodSumPirce() {
		return order_goodSumPirce;
	}
	/**
	 * @param order_goodSumPirce 要设置的 order_goodSumPirce
	 */
	public void setOrder_goodSumPirce(String order_goodSumPirce) {
		this.order_goodSumPirce = order_goodSumPirce;
	}
	/**
	 * @return order_goodLogisticsPrice
	 */
	public String getOrder_goodLogisticsPrice() {
		return order_goodLogisticsPrice;
	}
	/**
	 * @param order_goodLogisticsPrice 要设置的 order_goodLogisticsPrice
	 */
	public void setOrder_goodLogisticsPrice(String order_goodLogisticsPrice) {
		this.order_goodLogisticsPrice = order_goodLogisticsPrice;
	}
	/**
	 * @return order_state
	 */
	public String getOrder_state() {
		return order_state;
	}
	/**
	 * @param order_state 要设置的 order_state
	 */
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	/**
	 * @return order_ShopName
	 */
	public String getOrder_ShopName() {
		return order_ShopName;
	}
	/**
	 * @param order_ShopName 要设置的 order_ShopName
	 */
	public void setOrder_ShopName(String order_ShopName) {
		this.order_ShopName = order_ShopName;
	}
}
