package com.maibaduoduo.configuration.exception;

public class BaseUnCheckException extends RuntimeException implements BaseException {
    private static final long serialVersionUID = -778887391066124051L;

    /**
     * 异常信息
     */
    protected String message;

    /**
     * 具体异常码
     */
    protected int code;
    public BaseUnCheckException(Throwable throwable){
        super(throwable);
    }
    public BaseUnCheckException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseUnCheckException(int code, String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }
}
