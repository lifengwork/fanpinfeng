/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.inventory.facade.api;

import com.maibaduoduo.api.ApiFacade;
import com.maibaduoduo.configuration.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Component
@FeignClient(name = "program-service",configuration = {InventoryRestTemplateConfiguration.class},fallbackFactory = InventoryApiFallbackFactory.class)
public interface InventoryFacade extends ApiFacade {


    @RequestMapping("inventory/inventory/judgeWarehouse")
    public R judgeWarehouse(@RequestParam Map<String, Object> params);
}
