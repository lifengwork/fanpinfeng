package com.maibaduoduo.common.utils;

import java.io.Serializable;

public class ResultStatus implements Serializable {
    private int code;
    private String msg;
    private ResultStatus(){}
    public ResultStatus(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public static ResultStatus SUCCESS=new ResultStatus(0,"success");
    public static ResultStatus ERROR=new ResultStatus(9999,"error");

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
