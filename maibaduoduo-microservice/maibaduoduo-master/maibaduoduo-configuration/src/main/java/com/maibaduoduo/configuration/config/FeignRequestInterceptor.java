package com.maibaduoduo.configuration.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Created by Administrator on 2019/11/17 0017.
 */
public class FeignRequestInterceptor  implements RequestInterceptor {
    /**
     * 设置请求相关信息
     * @param template
     */
    public void apply(RequestTemplate template) {
        template.header("Content-Type", "application/json");
    }
}