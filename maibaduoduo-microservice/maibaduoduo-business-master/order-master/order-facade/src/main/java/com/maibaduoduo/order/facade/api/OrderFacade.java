/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.facade.api;

import com.maibaduoduo.api.ApiFacade;
import com.maibaduoduo.configuration.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@FeignClient(name = "order-service", configuration = {OrderRestTemplateConfiguration.class},  fallbackFactory = OrderApiFallbackFactory.class)
public interface OrderFacade extends ApiFacade {

    @GetMapping("/info/{id}")
    @ApiOperation("根据订单编码获取订单信息")
    public R info(@PathVariable("id") Long id);

}
