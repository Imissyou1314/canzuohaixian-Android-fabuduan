/**
 * 
 */
package com.zhanjixun.domain;

/**
 * @author Imissyou
 * @Time  2015年11月27日
 *  订单详情的子页面
 */
public class Order_GoodItem {
	
	//商品的图片URL
	private String image_url;
	//商品的商品名
	private String good_name;
	//商品的商品规格
	private String good_size;
	//商品的商品价格
	private String good_price;
	//商品的商品数量
	private String good_number;
	//商品的商品计价方式(论斤与论条)
	private String good_mode;
	/**
	 * @return good_mode
	 */
	public String getGood_mode() {
		return good_mode;
	}
	/**
	 * @param good_mode 要设置的 good_mode
	 */
	public void setGood_mode(String good_mode) {
		this.good_mode = good_mode;
	}
	/**
	 * @return image_url
	 */
	public String getImage_url() {
		return image_url;
	}
	/**
	 * @param image_url 要设置的 image_url
	 */
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	/**
	 * @return good_name
	 */
	public String getGood_name() {
		return good_name;
	}
	/**
	 * @param good_name 要设置的 good_name
	 */
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	/**
	 * @return good_size
	 */
	public String getGood_size() {
		return good_size;
	}
	/**
	 * @param good_size 要设置的 good_size
	 */
	public void setGood_size(String good_size) {
		this.good_size = good_size;
	}
	/**
	 * @return good_price
	 */
	public String getGood_price() {
		return good_price;
	}
	/**
	 * @param good_price 要设置的 good_price
	 */
	public void setGood_price(String good_price) {
		this.good_price = good_price;
	}
	/**
	 * @return good_number
	 */
	public String getGood_number() {
		return good_number;
	}
	/**
	 * @param good_number 要设置的 good_number
	 */
	public void setGood_number(String good_number) {
		this.good_number = good_number;
	}
	

}
