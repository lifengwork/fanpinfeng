/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.configuration.exception;

/**
 * 自定义异常
 *
 * @author Mark lifengwork@yeah.net
 */
public class SaasException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public SaasException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public SaasException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public SaasException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public SaasException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
