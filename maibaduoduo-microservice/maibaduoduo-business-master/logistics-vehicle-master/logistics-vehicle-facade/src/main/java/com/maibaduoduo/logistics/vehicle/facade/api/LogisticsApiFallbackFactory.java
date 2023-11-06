/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.vehicle.facade.api;

import com.maibaduoduo.api.ApiFallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class LogisticsApiFallbackFactory extends ApiFallbackFactory {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public LogisticsFacade fallbackFactory(Throwable throwable) {
        return null;
    }
}
