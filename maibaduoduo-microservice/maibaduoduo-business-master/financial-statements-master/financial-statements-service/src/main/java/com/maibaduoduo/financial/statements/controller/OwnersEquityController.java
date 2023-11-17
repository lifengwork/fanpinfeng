/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.financial.statements.entity.OwnersEquityEntity;
import com.maibaduoduo.financial.statements.service.OwnersEquityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 所有者权益表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:12:24
 */
@RestController
@RequestMapping("ownersequity/ownersequity")
public class OwnersEquityController {
    @Autowired
    private OwnersEquityService ownersEquityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ownersequity:ownersequity:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ownersEquityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("ownersequity:ownersequity:info")
    public R info(@PathVariable("id") Long id){
		OwnersEquityEntity ownersEquity = ownersEquityService.getById(id);

        return R.ok().put("ownersEquity", ownersEquity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("ownersequity:ownersequity:save")
    public R save(@RequestBody OwnersEquityEntity ownersEquity){
		ownersEquityService.save(ownersEquity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("ownersequity:ownersequity:update")
    public R update(@RequestBody OwnersEquityEntity ownersEquity){
		ownersEquityService.updateById(ownersEquity);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("ownersequity:ownersequity:delete")
    public R delete(@RequestBody Long[] ids){
		ownersEquityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
