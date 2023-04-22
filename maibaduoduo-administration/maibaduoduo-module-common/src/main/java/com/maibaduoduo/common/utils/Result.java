package com.maibaduoduo.common.utils;

import java.io.Serializable;

/**
  api接口返回类
 @author Administrator
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Integer CODE = 0;

    private Integer code;
    private String msg;
    private Object result;
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Result() {

    }

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }

    public Result(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public Result(Object result) {
        this.result = result;
    }

    public static Result error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    public static Result fail(String msg) {
        return error(500, msg);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

    /**

     带通用返回数据
     @param msg
     @param result
     @return
     */
    public static Result ok(String msg, Object result) {
        return new Result(CODE, msg, result);
    }
    /**

     不带数据
     @param msg
     @return
     */
    public static Result ok(String msg) {
        return new Result(CODE, msg);
    }
    /**

     带数据
     @param result
     @return
     */
    public static Result ok(Object result) {
        return new Result(CODE, result);
    }
    /**

     只带状态码
     @return
     */
    public static Result ok() {
        return new Result(CODE);
    }

    public Result message(String message){
            this.msg= message;
            return this;
    }
}
