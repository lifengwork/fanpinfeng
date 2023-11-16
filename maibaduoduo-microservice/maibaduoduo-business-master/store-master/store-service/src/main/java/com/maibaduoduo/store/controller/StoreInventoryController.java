/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.store.entity.StoreInventoryEntity;
import com.maibaduoduo.store.service.StoreInventoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 仓储库存
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
@RestController
@RequestMapping("storeinventory/storeinventory")
public class StoreInventoryController {
    @Autowired
    private StoreInventoryService storeInventoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storeinventory:storeinventory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = storeInventoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("storeinventory:storeinventory:info")
    public R info(@PathVariable("id") Long id){
		StoreInventoryEntity storeInventory = storeInventoryService.getById(id);

        return R.ok().put("storeInventory", storeInventory);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("storeinventory:storeinventory:save")
    public R save(@RequestBody StoreInventoryEntity storeInventory){
		storeInventoryService.save(storeInventory);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("storeinventory:storeinventory:update")
    public R update(@RequestBody StoreInventoryEntity storeInventory){
		storeInventoryService.updateById(storeInventory);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("storeinventory:storeinventory:delete")
    public R delete(@RequestBody Long[] ids){
		storeInventoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
