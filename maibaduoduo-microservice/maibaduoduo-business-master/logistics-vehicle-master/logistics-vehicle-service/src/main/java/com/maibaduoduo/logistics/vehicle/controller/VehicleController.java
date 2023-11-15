/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.vehicle.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.logistics.vehicle.entity.VehicleEntity;
import com.maibaduoduo.logistics.vehicle.service.VehicleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 具体交通工具管理表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-14 10:20:32
 */
@RestController
@RequestMapping("vehicle/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("vehicle:vehicle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = vehicleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("vehicle:vehicle:info")
    public R info(@PathVariable("id") Long id){
		VehicleEntity vehicle = vehicleService.getById(id);

        return R.ok().put("vehicle", vehicle);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("vehicle:vehicle:save")
    public R save(@RequestBody VehicleEntity vehicle){
		vehicleService.save(vehicle);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("vehicle:vehicle:update")
    public R update(@RequestBody VehicleEntity vehicle){
		vehicleService.updateById(vehicle);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("vehicle:vehicle:delete")
    public R delete(@RequestBody Long[] ids){
		vehicleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
