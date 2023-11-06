/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.vehicle.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.logistics.vehicle.entity.LogisticsVehicleEntity;
import com.maibaduoduo.logistics.service.LogisticsVehicleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 车辆
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-06-25 09:24:11
 */
@RestController
@RequestMapping("logisticsvehicle/logisticsvehicle")
public class LogisticsVehicleController {
    @Autowired
    private LogisticsVehicleService logisticsVehicleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("logisticsvehicle:logisticsvehicle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logisticsVehicleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("logisticsvehicle:logisticsvehicle:info")
    public R info(@PathVariable("id") Long id){
		LogisticsVehicleEntity logisticsVehicle = logisticsVehicleService.getById(id);

        return R.ok().put("logisticsVehicle", logisticsVehicle);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("logisticsvehicle:logisticsvehicle:save")
    public R save(@RequestBody LogisticsVehicleEntity logisticsVehicle){
		logisticsVehicleService.save(logisticsVehicle);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("logisticsvehicle:logisticsvehicle:update")
    public R update(@RequestBody LogisticsVehicleEntity logisticsVehicle){
		logisticsVehicleService.updateById(logisticsVehicle);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("logisticsvehicle:logisticsvehicle:delete")
    public R delete(@RequestBody Long[] ids){
		logisticsVehicleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
