/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maibaduoduo.order.facade.api;
import feign.Feign;
import feign.InvocationHandlerFactory;
import org.dromara.myth.springcloud.feign.MythFeignCircuitBreakerInvocationHandler;
import org.dromara.myth.springcloud.feign.MythFeignHandler;
import org.dromara.myth.springcloud.feign.MythRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.InvocationHandler;
import java.util.Objects;

/**
 * MythRestTemplateConfiguration.
 *
 * @author xiaoyu
 */
@Configuration
public class OrderRestTemplateConfiguration {
    /**
     * Feign builder feign . builder.
     * @return the feign . builder
     */
    @Autowired
    private CircuitBreakerFactory factory;

    @Autowired
    private OrderApiFallbackFactory orderApiFallbackFactory;

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
            return new MythFeignCircuitBreakerInvocationHandler(factory, target, dispatch, orderApiFallbackFactory);
        };
    }
}
