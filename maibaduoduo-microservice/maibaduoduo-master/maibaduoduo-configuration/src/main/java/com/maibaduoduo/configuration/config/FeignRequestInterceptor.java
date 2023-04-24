package com.maibaduoduo.configuration.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/11/17 0017.
 */
@Component
public class FeignRequestInterceptor  implements RequestInterceptor {
    /**
     * 设置请求相关信息
     * @param template
     */
    public void apply(RequestTemplate template) {
        template.header("Content-Type", "application/json");
    }
}