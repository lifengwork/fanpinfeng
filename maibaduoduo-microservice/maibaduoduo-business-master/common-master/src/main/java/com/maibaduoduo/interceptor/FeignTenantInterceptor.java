/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.interceptor;

import com.maibaduoduo.properties.BusinessContants;
import com.maibaduoduo.utils.BaseContextHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignTenantInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = BaseContextHandler.get(BusinessContants.AUTH_KEY);
        requestTemplate.header(BusinessContants.AUTH_KEY,token);
    }
}

