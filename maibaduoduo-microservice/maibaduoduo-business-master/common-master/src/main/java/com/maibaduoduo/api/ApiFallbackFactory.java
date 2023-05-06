/*
 * *
 *  * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *  *
 *  * SAAS系统设计研发交流
 *  *
 *  * https://www.maibaduoduo.com
 *
 */
package com.maibaduoduo.api;

import org.springframework.cloud.openfeign.FallbackFactory;

public abstract class ApiFallbackFactory implements FallbackFactory<ApiFacade> {
    public abstract ApiFacade fallbackFactory(Throwable cause);
    @Override
    public ApiFacade create(Throwable cause) {
        return this.fallbackFactory(cause);
    }
}
