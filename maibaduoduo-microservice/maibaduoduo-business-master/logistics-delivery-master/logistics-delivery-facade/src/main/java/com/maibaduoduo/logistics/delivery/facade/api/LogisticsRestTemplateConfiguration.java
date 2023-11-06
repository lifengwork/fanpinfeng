/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.delivery.facade.api;
import feign.Feign;
import feign.InvocationHandlerFactory;
import org.dromara.myth.springcloud.feign.MythFeignCircuitBreakerInvocationHandler;
import org.dromara.myth.springcloud.feign.MythFeignHandler;
import org.dromara.myth.springcloud.feign.MythRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * MythRestTemplateConfiguration.
 *
 * @author xiaoyu
 */
@Configuration
public class LogisticsRestTemplateConfiguration {
    /**
     * Feign builder feign . builder.
     * @return the feign . builder
     */
    @Autowired
    private CircuitBreakerFactory factory;

    @Autowired
    private LogisticsApiFallbackFactory programApiFallbackFactory;
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder().requestInterceptor(new MythRestTemplateInterceptor())
                .invocationHandlerFactory(mythFeignCircuitBreakerInvocationHandler());
    }

    /**
     * Invocation handler factory invocation handler factory.
     * @return the invocation handler factory
     */
    @Bean
    public InvocationHandlerFactory invocationHandlerFactory() {
        return (target, dispatch) -> {
            MythFeignHandler handler = new MythFeignHandler();
            handler.setTarget(target);
            handler.setHandlers(dispatch);
            return handler;
        };
    }

    /**
     * mythFeignCircuitBreakerInvocationHandler
     * @param
     * @return
     */
    public InvocationHandlerFactory mythFeignCircuitBreakerInvocationHandler() {
        return (target, dispatch) -> {
            return new MythFeignCircuitBreakerInvocationHandler(factory, target, dispatch, programApiFallbackFactory);
        };
    }
}
