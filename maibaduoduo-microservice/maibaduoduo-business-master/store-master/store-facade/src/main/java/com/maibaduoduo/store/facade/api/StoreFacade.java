/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.facade.api;

import com.maibaduoduo.api.ApiFacade;
import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.store.entity.StoreEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "store-service", configuration = {StoreRestTemplateConfiguration.class}, fallbackFactory = StoreApiFallbackFactory.class)
public interface StoreFacade extends ApiFacade {

    @GetMapping("/saas/store/info/{id}")
    @ApiOperation("测试获取仓库信息")
    public R info(@PathVariable("id") Long id);

    @PostMapping("saas/store/stockup")
    R stockUp(@RequestBody String stockInfo);
}
