/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.store.entity.FreightChargeEntity;
import com.maibaduoduo.store.service.FreightChargeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 运费
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
@RestController
@RequestMapping("freightcharge/freightcharge")
public class FreightChargeController {
    @Autowired
    private FreightChargeService freightChargeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("freightcharge:freightcharge:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = freightChargeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("freightcharge:freightcharge:info")
    public R info(@PathVariable("id") Long id){
		FreightChargeEntity freightCharge = freightChargeService.getById(id);

        return R.ok().put("freightCharge", freightCharge);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("freightcharge:freightcharge:save")
    public R save(@RequestBody FreightChargeEntity freightCharge){
		freightChargeService.save(freightCharge);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("freightcharge:freightcharge:update")
    public R update(@RequestBody FreightChargeEntity freightCharge){
		freightChargeService.updateById(freightCharge);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("freightcharge:freightcharge:delete")
    public R delete(@RequestBody Long[] ids){
		freightChargeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
