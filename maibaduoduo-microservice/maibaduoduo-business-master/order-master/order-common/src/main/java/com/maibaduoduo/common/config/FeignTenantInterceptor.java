package com.maibaduoduo.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignTenantInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = BaseContextHandler.get("token-app");
        requestTemplate.header("token-app",token);
    }
}

