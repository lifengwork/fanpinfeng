/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.scp.facade.api;

import com.maibaduoduo.api.ApiFacade;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


@Component
@FeignClient(name = "program-service",configuration = {ScpRestTemplateConfiguration.class},fallbackFactory = ScpApiFallbackFactory.class)
public interface ScpFacade extends ApiFacade {

}
