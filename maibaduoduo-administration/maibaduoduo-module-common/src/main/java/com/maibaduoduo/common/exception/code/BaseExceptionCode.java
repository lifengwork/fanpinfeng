package com.maibaduoduo.common.exception.code;

/**
 * @author maibaduoduo
 * @createTime 2017-12-25 13:46
 */
public interface BaseExceptionCode {
    /**
     * 异常编码
     *
     * @return
     */
    int getCode();

    /**
     * 异常消息
     *
     * @return
     */
    String getMsg();
}
