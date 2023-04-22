package com.maibaduoduo.common.exception;

import com.maibaduoduo.common.exception.code.BaseExceptionCode;

/**
 * 业务异常
 * 用于在处理业务逻辑时，进行抛出的异常。
 *
 * @author maibaduoduo
 * @version 1.0,
 * @see Exception
 */
public class OpException extends BaseUncheckedException {

    private static final long serialVersionUID = -3843907364558373817L;

    public OpException(String message) {
        super(-1, message);
    }

    public OpException(int code, String message) {
        super(code, message);
    }

    public OpException(int code, String message, Object... args) {
        super(code, message, args);
    }

    /**
     * 实例化异常
     *
     * @param code    自定义异常编码
     * @param message 自定义异常消息
     * @param args    已定义异常参数
     * @return
     */
    public static OpException wrap(int code, String message, Object... args) {
        return new OpException(code, message, args);
    }

    public static OpException wrap(String message, Object... args) {
        return new OpException(-1, message, args);
    }

    public static OpException validFail(String message, Object... args) {
        return new OpException(-9, message, args);
    }

    public static OpException wrap(BaseExceptionCode ex) {
        return new OpException(ex.getCode(), ex.getMsg());
    }

    @Override
    public String toString() {
        return "BizException [message=" + message + ", code=" + code + "]";
    }

}
