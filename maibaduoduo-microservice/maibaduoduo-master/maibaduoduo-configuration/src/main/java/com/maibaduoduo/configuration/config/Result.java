package com.maibaduoduo.configuration.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/11/17 0017.
 */
public class Result<T> {
    private final String DEFAULT_PATTERN="yyyy-MM-dd";
    private final String DEFAULT_PATTERN_TIME="yyyy-MM-dd HH:mm:ss";
    private final String DEFAULT_PATTERN_YEAR="yyyy";
    private final String DEFAULT_PATTERN_YM="yyyy-MM";
    public Result(T data, int code, String message) {
        this.result = data;
        this.code = code;
        this.message = message;
        this.responseTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN_TIME));
    }

    public Result(T data, StatusCode statusCode) {
        this.result = data;
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.responseTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN_TIME));
    }

    public Result(T data, StatusCode statusCode, String extendMsg) {
        this.result = data;
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage() + extendMsg;
        this.responseTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN_TIME));
    }

    public Result(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.responseTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN_TIME));
    }

    public Result(StatusCode statusCode, String extendMsg) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage() + extendMsg;
        this.responseTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN_TIME));
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.responseTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN_TIME));
    }

    public Result(int code) {
        this.code = code;
        this.responseTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN_TIME));
    }

    private int code;

    private String message;

    private T result;

    private String responseTime;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public Map toMap() {
        Map out = new HashMap();
        out.put("code", code);
        out.put("message", message);
        out.put("result", result);
        out.put("responseTime", responseTime);
        return out;
    }

    @Override
    public String toString() {
        return toMap().toString();
    }

}