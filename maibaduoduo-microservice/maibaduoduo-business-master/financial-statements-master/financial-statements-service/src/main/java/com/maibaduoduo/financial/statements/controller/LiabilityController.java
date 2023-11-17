/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.financial.statements.entity.LiabilityEntity;
import com.maibaduoduo.financial.statements.service.LiabilityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 负债表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:12:24
 */
@RestController
@RequestMapping("liability/liability")
public class LiabilityController {
    @Autowired
    private LiabilityService liabilityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("liability:liability:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = liabilityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("liability:liability:info")
    public R info(@PathVariable("id") Long id){
		LiabilityEntity liability = liabilityService.getById(id);

        return R.ok().put("liability", liability);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("liability:liability:save")
    public R save(@RequestBody LiabilityEntity liability){
		liabilityService.save(liability);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("liability:liability:update")
    public R update(@RequestBody LiabilityEntity liability){
		liabilityService.updateById(liability);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("liability:liability:delete")
    public R delete(@RequestBody Long[] ids){
		liabilityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
