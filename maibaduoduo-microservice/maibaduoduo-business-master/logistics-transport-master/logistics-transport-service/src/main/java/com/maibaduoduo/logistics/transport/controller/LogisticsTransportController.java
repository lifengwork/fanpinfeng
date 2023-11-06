/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.transport.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.logistics.transport.entity.LogisticsTransportEntity;
import com.maibaduoduo.logistics.service.LogisticsTransportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 运输
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-06-25 09:24:11
 */
@RestController
@RequestMapping("logisticstransport/logisticstransport")
public class LogisticsTransportController {
    @Autowired
    private LogisticsTransportService logisticsTransportService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("logisticstransport:logisticstransport:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logisticsTransportService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("logisticstransport:logisticstransport:info")
    public R info(@PathVariable("id") Long id){
		LogisticsTransportEntity logisticsTransport = logisticsTransportService.getById(id);

        return R.ok().put("logisticsTransport", logisticsTransport);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("logisticstransport:logisticstransport:save")
    public R save(@RequestBody LogisticsTransportEntity logisticsTransport){
		logisticsTransportService.save(logisticsTransport);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("logisticstransport:logisticstransport:update")
    public R update(@RequestBody LogisticsTransportEntity logisticsTransport){
		logisticsTransportService.updateById(logisticsTransport);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("logisticstransport:logisticstransport:delete")
    public R delete(@RequestBody Long[] ids){
		logisticsTransportService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
