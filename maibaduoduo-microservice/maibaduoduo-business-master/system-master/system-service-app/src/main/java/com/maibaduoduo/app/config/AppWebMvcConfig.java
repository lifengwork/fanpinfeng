package com.maibaduoduo.app.config;

import com.maibaduoduo.app.resolver.LoginUserHandlerMethodArgumentResolver;
import com.maibaduoduo.config.WebMvcConfig;
import com.maibaduoduo.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;

@Configuration
public class AppWebMvcConfig extends WebMvcConfig {
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;
    public AppWebMvcConfig(AuthorizationInterceptor authorizationInterceptor,LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver) {
        super(authorizationInterceptor);
        this.loginUserHandlerMethodArgumentResolver = loginUserHandlerMethodArgumentResolver;
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }
}
