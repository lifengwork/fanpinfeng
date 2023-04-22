/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.configuration.config;

/**
 * Created by Administrator on 2019/11/17 0017.
 */
public class BusinessException extends RuntimeException{

    private Integer code;
    private String message;
    public BusinessException() {
    }
    public BusinessException(String errmsg) {
        super(errmsg);
        this.code=2;
        this.message=errmsg;
    }
    public BusinessException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.code=statusCode.getCode();
        this.message=statusCode.getMessage();
    }

    public BusinessException(int code ,String errmsg) {
        super(errmsg);
        this.code=code;
        this.message=errmsg;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"message\":\"" + message + "\"" +"}";
    }
}