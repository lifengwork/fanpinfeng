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

import com.maibaduoduo.scp.entity.ScpStoreEntity;
import com.maibaduoduo.scp.service.ScpStoreService;
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
@RequestMapping("scpstore/scpstore")
public class ScpStoreController {
    @Autowired
    private ScpStoreService scpStoreService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("scpstore:scpstore:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scpStoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("scpstore:scpstore:info")
    public R info(@PathVariable("id") Long id){
		ScpStoreEntity scpStore = scpStoreService.getById(id);

        return R.ok().put("scpStore", scpStore);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("scpstore:scpstore:save")
    public R save(@RequestBody ScpStoreEntity scpStore){
		scpStoreService.save(scpStore);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("scpstore:scpstore:update")
    public R update(@RequestBody ScpStoreEntity scpStore){
		scpStoreService.updateById(scpStore);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("scpstore:scpstore:delete")
    public R delete(@RequestBody Long[] ids){
		scpStoreService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
