package com.zhanjixun.domain2;

import java.util.Map;

public class BaseResult {
	/**
	 * 结果状态
	 */
	private Boolean serviceResult;
	/**
	 * 结果提示信息
	 */
	private String resultInfo;
	/**
	 * 结果信息,不跟服务器同步
	 */
	private Map<String, String> resultParam;

	/**
	 * @return serviceResult
	 */
	public Boolean getServiceResult() {
		return serviceResult;
	}

	/**
	 * @param serviceResult
	 *            要设置的 serviceResult
	 */
	public void setServiceResult(Boolean serviceResult) {
		this.serviceResult = serviceResult;
	}

	/**
	 * @return resultInfo
	 */
	public String getResultInfo() {
		return resultInfo;
	}

	/**
	 * @param resultInfo
	 *            要设置的 resultInfo
	 */
	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

	/**
	 * @return resultParm
	 */
	public Map<String, String> getResultParam() {
		return resultParam;
	}

	/**
	 * @param resultParm
	 *            要设置的 resultParm
	 */
	public void setResultParam(Map<String, String> resultParm) {
		this.resultParam = resultParm;
	}
}
