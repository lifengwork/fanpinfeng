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
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@FeignClient(name = "program-service",configuration = {ProgramRestTemplateConfiguration.class},fallbackFactory = ProgramApiFallbackFactory.class)
public interface ProgramFacade extends ApiFacade {

}
