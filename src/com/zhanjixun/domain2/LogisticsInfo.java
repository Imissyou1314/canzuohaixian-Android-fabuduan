package com.zhanjixun.domain2;

import java.util.List;

/**
 * 物流信息
 * 
 * @author 詹命天子
 *
 */
public class LogisticsInfo {

	private String message;
	private String nu;
	private Integer ischeck;
	private String com;
	private Integer status;
	private String condition;
	private Integer state;
	private List<Logistics> data;
	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message 要设置的 message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return nu
	 */
	public String getNu() {
		return nu;
	}
	/**
	 * @param nu 要设置的 nu
	 */
	public void setNu(String nu) {
		this.nu = nu;
	}
	/**
	 * @return ischeck
	 */
	public Integer getIscheck() {
		return ischeck;
	}
	/**
	 * @param ischeck 要设置的 ischeck
	 */
	public void setIscheck(Integer ischeck) {
		this.ischeck = ischeck;
	}
	/**
	 * @return com
	 */
	public String getCom() {
		return com;
	}
	/**
	 * @param com 要设置的 com
	 */
	public void setCom(String com) {
		this.com = com;
	}
	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status 要设置的 status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return condition
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition 要设置的 condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * @return state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state 要设置的 state
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return data
	 */
	public List<Logistics> getData() {
		return data;
	}
	/**
	 * @param data 要设置的 data
	 */
	public void setData(List<Logistics> data) {
		this.data = data;
	}

}
