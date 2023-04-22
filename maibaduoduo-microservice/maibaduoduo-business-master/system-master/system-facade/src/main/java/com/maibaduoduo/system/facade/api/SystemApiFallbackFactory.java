/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.system.facade.api;

import com.maibaduoduo.common.form.LoginForm;
import com.maibaduoduo.common.utils.R;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用户API熔断器
 */
@Component
public class SystemApiFallbackFactory implements FallbackFactory<SystemFacade> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public SystemFacade create(Throwable throwable) {
        throwable.fillInStackTrace().printStackTrace();
        return new SystemFacade() {

            @Override
            public R login(LoginForm form) {
                return R.error();
            }
        };
    }
}
