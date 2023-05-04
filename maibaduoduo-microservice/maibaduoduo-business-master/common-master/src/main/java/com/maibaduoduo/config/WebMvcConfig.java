/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.config;

import com.maibaduoduo.interceptor.AuthorizationInterceptor;
import com.maibaduoduo.properties.InterceptorProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description Web配置类对单个服务做进一步拦截处理如果需要。
 * @Param
 * @Author ${USER}
 * @return ${RETURN}
 * @Date ${DATE} ${TIME}
 */
@Configuration
@EnableConfigurationProperties(value = {InterceptorProperties.class})
@ConditionalOnExpression("'true'.equals('${maibaduoduo.interceptor.path.isRegis}')")
public class WebMvcConfig implements WebMvcConfigurer {
    private AuthorizationInterceptor authorizationInterceptor;
    public WebMvcConfig(AuthorizationInterceptor authorizationInterceptor){
        this.authorizationInterceptor = authorizationInterceptor;
    }
    /**
     * 增加要拦截的PATH
     * "/saas/**","/app/**"
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns(InterceptorProperties.paths)
                .excludePathPatterns(InterceptorProperties.excludePaths);*/
    }
}