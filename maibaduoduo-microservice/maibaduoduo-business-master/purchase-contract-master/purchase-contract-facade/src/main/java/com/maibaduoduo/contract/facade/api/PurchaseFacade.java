/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.purchase.facade.api;

import com.maibaduoduo.api.ApiFacade;
import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.purchase.entity.PurchaseEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Component
@FeignClient(name = "program-service",configuration = {PurchaseRestTemplateConfiguration.class},fallbackFactory = PurchaseApiFallbackFactory.class)
public interface PurchaseFacade extends ApiFacade {
    @PostMapping("/purchase/purchase/save")
    R doPurchase(@RequestBody PurchaseEntity purchase);
}
