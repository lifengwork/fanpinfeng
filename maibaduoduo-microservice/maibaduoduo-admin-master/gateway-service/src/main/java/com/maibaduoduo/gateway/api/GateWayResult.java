package com.maibaduoduo.gateway.api;

/**
 * @Desc: //TODO
 * @Author: fanpingfeng
 * @Date 2023-4-21
 */
public class GateWayResult<T> {
    private long code;
    private String message;
    private T data;

    protected GateWayResult() {
    }

    protected GateWayResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> GateWayResult<T> success(T data) {
        return new GateWayResult<T>(ResultStatusCode.SUCCESS.getCode(), ResultStatusCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> GateWayResult<T> success(T data, String message) {
        return new GateWayResult<T>(ResultStatusCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> GateWayResult<T> failed(IResult errorCode) {
        return new GateWayResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> GateWayResult<T> failed(IResult errorCode, String message) {
        return new GateWayResult<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> GateWayResult<T> failed(String message) {
        return new GateWayResult<T>(ResultStatusCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> GateWayResult<T> failed() {
        return failed(ResultStatusCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> GateWayResult<T> validateFailed() {
        return failed(ResultStatusCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> GateWayResult<T> validateFailed(String message) {
        return new GateWayResult<T>(ResultStatusCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> GateWayResult<T> unauthorized(T data) {
        return new GateWayResult<T>(ResultStatusCode.UNAUTHORIZED.getCode(), ResultStatusCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> GateWayResult<T> forbidden(T data) {
        return new GateWayResult<T>(ResultStatusCode.FORBIDDEN.getCode(), ResultStatusCode.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
