package com.maibaduoduo.configuration.config;

import lombok.Data;

/**
 * Created by Administrator on 2019/11/17 0017.
 */
@Data
public class ExceptionInfo {
    private Long timestamp;
    private Integer status;
    private String exception;
    private String message;
    private String path;
    private String error;
}
