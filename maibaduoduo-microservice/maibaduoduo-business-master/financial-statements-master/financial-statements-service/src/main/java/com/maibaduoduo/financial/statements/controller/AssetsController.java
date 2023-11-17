/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.financial.statements.entity.AssetsEntity;
import com.maibaduoduo.financial.statements.service.AssetsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 资产表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:12:24
 */
@RestController
@RequestMapping("assets/assets")
public class AssetsController {
    @Autowired
    private AssetsService assetsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("assets:assets:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = assetsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("assets:assets:info")
    public R info(@PathVariable("id") Long id){
		AssetsEntity assets = assetsService.getById(id);

        return R.ok().put("assets", assets);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("assets:assets:save")
    public R save(@RequestBody AssetsEntity assets){
		assetsService.save(assets);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("assets:assets:update")
    public R update(@RequestBody AssetsEntity assets){
		assetsService.updateById(assets);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("assets:assets:delete")
    public R delete(@RequestBody Long[] ids){
		assetsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
