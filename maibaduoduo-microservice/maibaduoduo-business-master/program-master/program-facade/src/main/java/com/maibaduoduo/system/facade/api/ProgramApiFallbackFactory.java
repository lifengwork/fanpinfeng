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
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ProgramApiFallbackFactory extends ApiFallbackFactory {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public ProgramFacade fallbackFactory(Throwable throwable) {
        return null;
    }
}
