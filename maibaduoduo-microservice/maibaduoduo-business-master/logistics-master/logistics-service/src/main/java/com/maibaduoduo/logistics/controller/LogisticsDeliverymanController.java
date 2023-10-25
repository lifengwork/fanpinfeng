/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.logistics.entity.LogisticsDeliverymanEntity;
import com.maibaduoduo.logistics.service.LogisticsDeliverymanService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 配送员
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-10-25 18:03:06
 */
@RestController
@RequestMapping("logisticsdeliveryman/logisticsdeliveryman")
public class LogisticsDeliverymanController {
    @Autowired
    private LogisticsDeliverymanService logisticsDeliverymanService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("logisticsdeliveryman:logisticsdeliveryman:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logisticsDeliverymanService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("logisticsdeliveryman:logisticsdeliveryman:info")
    public R info(@PathVariable("id") Long id){
		LogisticsDeliverymanEntity logisticsDeliveryman = logisticsDeliverymanService.getById(id);

        return R.ok().put("logisticsDeliveryman", logisticsDeliveryman);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("logisticsdeliveryman:logisticsdeliveryman:save")
    public R save(@RequestBody LogisticsDeliverymanEntity logisticsDeliveryman){
		logisticsDeliverymanService.save(logisticsDeliveryman);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("logisticsdeliveryman:logisticsdeliveryman:update")
    public R update(@RequestBody LogisticsDeliverymanEntity logisticsDeliveryman){
		logisticsDeliverymanService.updateById(logisticsDeliveryman);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("logisticsdeliveryman:logisticsdeliveryman:delete")
    public R delete(@RequestBody Long[] ids){
		logisticsDeliverymanService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
