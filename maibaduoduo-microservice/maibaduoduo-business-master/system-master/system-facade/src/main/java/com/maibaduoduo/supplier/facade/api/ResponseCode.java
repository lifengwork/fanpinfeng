/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.supplier.facade.api;

public class ResponseCode {

	/**
	 * OK
	 */
	public static final int CODE_OK = 200;

	/**
	 * 服务器内部错误
	 */
	public static final int CODE_INTERNAL_SERVER_ERROR = 500;

	/**
	 * 连接错误的其他状态码
	 */
	public static final int OTHER_ERROR_CODE = -200;
	/**
	 * Request Method:POST Status Code:200 OK --业务逻辑错误
	 */
	public static final int BUSINESS_SUB_CODE = 10000;
}
