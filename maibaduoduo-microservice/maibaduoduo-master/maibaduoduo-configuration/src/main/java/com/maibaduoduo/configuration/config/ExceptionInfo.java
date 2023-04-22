package com.maibaduoduo.configuration.config;

import lombok.Data;

/**
 * Created by Administrator on 2019/11/17 0017.
 */
@Data
public class ExceptionInfo {
    private Long timestamp;
    private Integer status;
    //异常包结构-"com.crazy.cloud.common.exception.DataConflictException"
    private String exception;
    //信息--手机已注册
    private String message;
    private String path;
    private String error;
}
