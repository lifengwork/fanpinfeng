/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.system.facade.api;

import com.maibaduoduo.api.ApiFacade;
import com.maibaduoduo.configuration.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "system-service", configuration = {SystemRestTemplateConfiguration.class},fallbackFactory = SystemApiFallbackFactory.class)
public interface SystemFacade extends ApiFacade {
    @GetMapping("/sys/user/info/m/{mobile}")
    R infoByMobile(@PathVariable("mobile") String mobile);

}
