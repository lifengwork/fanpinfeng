/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.transport.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.logistics.transport.entity.AirwayBolEntity;
import com.maibaduoduo.logistics.transport.service.AirwayBolService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-20 10:53:45
 */
@RestController
@RequestMapping("airwaybol/airwaybol")
public class AirwayBolController {
    @Autowired
    private AirwayBolService airwayBolService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("airwaybol:airwaybol:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = airwayBolService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("airwaybol:airwaybol:info")
    public R info(@PathVariable("id") Long id){
		AirwayBolEntity airwayBol = airwayBolService.getById(id);

        return R.ok().put("airwayBol", airwayBol);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("airwaybol:airwaybol:save")
    public R save(@RequestBody AirwayBolEntity airwayBol){
		airwayBolService.save(airwayBol);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("airwaybol:airwaybol:update")
    public R update(@RequestBody AirwayBolEntity airwayBol){
		airwayBolService.updateById(airwayBol);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("airwaybol:airwaybol:delete")
    public R delete(@RequestBody Long[] ids){
		airwayBolService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
