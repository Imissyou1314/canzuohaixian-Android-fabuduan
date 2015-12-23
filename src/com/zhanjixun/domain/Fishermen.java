package com.zhanjixun.domain;

import java.util.Date;

/**
 * 渔户
 * 
 * @author 詹命天子
 *
 */
public class Fishermen extends Seller {
	/**
	 * 停靠港口
	 */
	private String shipPort;
	/**
	 * 到港时间
	 */
	private Date portTime;
	/**
	 * 捕捞方式
	 */
	private String getType;
	
	/**
	 * 主机功率
	 */
	private String enginePower;
	
	/**
	 *  船吨位数 
	 */
	private String tonnage;
	
	

	/**
	 * @return shipPort
	 */
	public String getShipPort() {
		return shipPort;
	}
	
	/**
	 * @param shipPort
	 *            要设置的 shipPort
	 */
	public void setShipPort(String shipPort) {
		this.shipPort = shipPort;
	}

	/**
	 * @return portTime
	 */
	public Date getPortTime() {
		return portTime;
	}

	/**
	 * @param portTime
	 *            要设置的 portTime
	 */
	public void setPortTime(Date portTime) {
		this.portTime = portTime;
	}

	/**
	 * @return getType
	 */
	public String getGetType() {
		return getType;
	}

	/**
	 * @param getType
	 *            要设置的 getType
	 */
	public void setGetType(String getType) {
		this.getType = getType;
	}

	public String getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(String enginePower) {
		this.enginePower = enginePower;
	}

	public String getTonnage() {
		return tonnage;
	}

	public void setTonnage(String tonnage) {
		this.tonnage = tonnage;
	}

	
}
