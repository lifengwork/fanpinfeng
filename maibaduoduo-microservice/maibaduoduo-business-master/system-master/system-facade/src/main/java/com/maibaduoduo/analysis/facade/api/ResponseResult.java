/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.analysis.facade.api;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * WinRetailResponse -- REST API的统一数据结构，用WinRetailResponse来封装。返回代码为200，或者0时为正常，其他为情况为错误情况
 * 
 */
@ApiModel
public class ResponseResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1917760115313349704L;
	@ApiModelProperty("接口状态码：200成功其他失败")
	private int code = 200; // 错误码,默认为200
	@ApiModelProperty("接口消息：默认null")
	private String message = StringUtils.EMPTY; // 消息
	@ApiModelProperty("业务状态码：1：成功，10000：服务器内部错误，其他状态见接口说明")
	private int subCode = ResponseCode.CODE_OK;
	@ApiModelProperty("业务消息：默认null")
	private String subMessage = StringUtils.EMPTY;
	@ApiModelProperty("业务对象")
	private T data; // 数据对象
	@ApiModelProperty("时间戳")
	private long timestamp; // 时间戳

	public int getCode() {
		return code;
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSubCode() {
		return subCode;
	}

	public void setSubCode(int subCode) {
		this.subCode = subCode;
	}

	public String getSubMessage() {
		return subMessage;
	}

	public void setSubMessage(String subMessage) {
		this.subMessage = subMessage;
	}

	public long getTimestamp() {
		long millis = System.currentTimeMillis();
		if (timestamp == 0) {
			timestamp = millis;
		}
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ResponseResult [code=" + code + ", message=" + message + ", subCode=" + subCode + ", subMessage="
				+ subMessage + ", data=" + data + ", timestamp=" + timestamp + "]";
	}
}
