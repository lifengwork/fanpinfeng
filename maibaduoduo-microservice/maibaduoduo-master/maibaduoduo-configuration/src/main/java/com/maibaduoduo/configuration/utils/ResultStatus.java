/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.configuration.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultStatus implements Serializable {
	private int code = 0;     //状态码
    private String message = "";   //提示信息
    
}
