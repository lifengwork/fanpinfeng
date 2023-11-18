/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.scp.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.scp.entity.ScpTransportEntity;
import com.maibaduoduo.scp.service.ScpTransportService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-18 21:50:57
 */
@RestController
@RequestMapping("scptransport/scptransport")
public class ScpTransportController {
    @Autowired
    private ScpTransportService scpTransportService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("scptransport:scptransport:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scpTransportService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("scptransport:scptransport:info")
    public R info(@PathVariable("id") Long id){
		ScpTransportEntity scpTransport = scpTransportService.getById(id);

        return R.ok().put("scpTransport", scpTransport);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("scptransport:scptransport:save")
    public R save(@RequestBody ScpTransportEntity scpTransport){
		scpTransportService.save(scpTransport);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("scptransport:scptransport:update")
    public R update(@RequestBody ScpTransportEntity scpTransport){
		scpTransportService.updateById(scpTransport);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("scptransport:scptransport:delete")
    public R delete(@RequestBody Long[] ids){
		scpTransportService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
