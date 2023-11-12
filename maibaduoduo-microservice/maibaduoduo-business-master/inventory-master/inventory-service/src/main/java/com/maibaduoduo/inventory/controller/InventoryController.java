/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.inventory.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.inventory.entity.InventoryEntity;
import com.maibaduoduo.inventory.service.InventoryService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 中央仓库
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
@RestController
@RequestMapping("inventory/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("inventory:inventory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = inventoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("inventory:inventory:info")
    public R info(@PathVariable("id") Long id){
		InventoryEntity inventory = inventoryService.getById(id);

        return R.ok().put("inventory", inventory);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("inventory:inventory:save")
    public R save(@RequestBody InventoryEntity inventory){
		inventoryService.save(inventory);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("inventory:inventory:update")
    public R update(@RequestBody InventoryEntity inventory){
		inventoryService.updateById(inventory);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("inventory:inventory:delete")
    public R delete(@RequestBody Long[] ids){
		inventoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
