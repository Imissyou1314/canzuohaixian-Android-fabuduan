/**
 * 
 */
package com.zhanjixun.domain;

import java.util.List;
import java.util.Map;

/**
 * @author Imissyou
 * @Time  2015年11月25日
 *
 */
public class OrderInformation {
	
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
	
	private List<Map<String,Object>> ordersDatil ;
	/**
	 * @return ordersDatil
	 */
	public List<Map<String, Object>> getOrdersDatil() {
		return ordersDatil;
	}
	/**
	 * @param ordersDatil 要设置的 ordersDatil
	 */
	public void setOrdersDatil(List<Map<String, Object>> ordersDatil) {
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
	

}
