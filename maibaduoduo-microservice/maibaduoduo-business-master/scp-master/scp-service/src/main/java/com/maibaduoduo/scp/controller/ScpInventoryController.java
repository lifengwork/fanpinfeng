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

import com.maibaduoduo.scp.entity.ScpInventoryEntity;
import com.maibaduoduo.scp.service.ScpInventoryService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-18 22:15:36
 */
@RestController
@RequestMapping("scpinventory/scpinventory")
public class ScpInventoryController {
    @Autowired
    private ScpInventoryService scpInventoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("scpinventory:scpinventory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scpInventoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("scpinventory:scpinventory:info")
    public R info(@PathVariable("id") Long id){
		ScpInventoryEntity scpInventory = scpInventoryService.getById(id);

        return R.ok().put("scpInventory", scpInventory);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("scpinventory:scpinventory:save")
    public R save(@RequestBody ScpInventoryEntity scpInventory){
		scpInventoryService.save(scpInventory);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("scpinventory:scpinventory:update")
    public R update(@RequestBody ScpInventoryEntity scpInventory){
		scpInventoryService.updateById(scpInventory);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("scpinventory:scpinventory:delete")
    public R delete(@RequestBody Long[] ids){
		scpInventoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
