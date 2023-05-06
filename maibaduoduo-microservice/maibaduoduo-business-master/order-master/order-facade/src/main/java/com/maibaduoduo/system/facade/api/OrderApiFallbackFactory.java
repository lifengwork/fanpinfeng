/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.system.facade.api;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.maibaduoduo.api.ApiFallbackFactory;
import com.maibaduoduo.configuration.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户API熔断器
 */
@Component
public class OrderApiFallbackFactory extends ApiFallbackFactory {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public OrderFacade fallbackFactory(Throwable throwable) {
        return new OrderFacade() {
            @Override
            public R info(Long id) {
                logger.error(ExceptionUtil.stacktraceToString(throwable));
                return R.error();
            }
        };
    }
}
