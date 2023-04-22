package com.maibaduoduo.configuration.exception;

public class SaasException extends BaseUnCheckException {
    private static final long serialVersionUID = -3843907364558373817L;

    public SaasException(Throwable throwable){super(throwable);}

    public SaasException(String message) {
        super(-1, message);
    }

    public SaasException(int code, String message) {
        super(code, message);
    }

    public SaasException(int code, String message, Object... args) {
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
    public static SaasException wrap(int code, String message, Object... args) {
        return new SaasException(code, message, args);
    }

    public static SaasException wrap(String message, Object... args) {
        return new SaasException(-1, message, args);
    }

    public static SaasException validFail(String message, Object... args) {
        return new SaasException(-9, message, args);
    }

    public static SaasException wrap(BaseExceptionCode ex) {
        return new SaasException(ex.getCode(), ex.getMsg());
    }

    @Override
    public String toString() {
        return "BizException [message=" + message + ", code=" + code + "]";
    }
}
