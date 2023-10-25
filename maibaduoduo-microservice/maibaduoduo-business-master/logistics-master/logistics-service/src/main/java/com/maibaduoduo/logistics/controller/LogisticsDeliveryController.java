/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.logistics.entity.LogisticsDeliveryEntity;
import com.maibaduoduo.logistics.service.LogisticsDeliveryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 配送
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-06-25 09:24:11
 */
@RestController
@RequestMapping("logisticsdelivery/logisticsdelivery")
public class LogisticsDeliveryController {
    @Autowired
    private LogisticsDeliveryService logisticsDeliveryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("logisticsdelivery:logisticsdelivery:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logisticsDeliveryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("logisticsdelivery:logisticsdelivery:info")
    public R info(@PathVariable("id") Long id){
		LogisticsDeliveryEntity logisticsDelivery = logisticsDeliveryService.getById(id);

        return R.ok().put("logisticsDelivery", logisticsDelivery);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("logisticsdelivery:logisticsdelivery:save")
    public R save(@RequestBody LogisticsDeliveryEntity logisticsDelivery){
		logisticsDeliveryService.save(logisticsDelivery);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("logisticsdelivery:logisticsdelivery:update")
    public R update(@RequestBody LogisticsDeliveryEntity logisticsDelivery){
		logisticsDeliveryService.updateById(logisticsDelivery);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("logisticsdelivery:logisticsdelivery:delete")
    public R delete(@RequestBody Long[] ids){
		logisticsDeliveryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
