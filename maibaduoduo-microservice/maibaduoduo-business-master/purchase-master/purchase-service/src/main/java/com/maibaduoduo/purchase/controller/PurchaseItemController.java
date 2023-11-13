/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.purchase.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.purchase.entity.PurchaseItemEntity;
import com.maibaduoduo.purchase.service.PurchaseItemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 21:11:37
 */
@RestController
@RequestMapping("purchaseitem/purchaseitem")
public class PurchaseItemController {
    @Autowired
    private PurchaseItemService purchaseItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("purchaseitem:purchaseitem:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseItemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("purchaseitem:purchaseitem:info")
    public R info(@PathVariable("id") Long id){
		PurchaseItemEntity purchaseItem = purchaseItemService.getById(id);

        return R.ok().put("purchaseItem", purchaseItem);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("purchaseitem:purchaseitem:save")
    public R save(@RequestBody PurchaseItemEntity purchaseItem){
		purchaseItemService.save(purchaseItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("purchaseitem:purchaseitem:update")
    public R update(@RequestBody PurchaseItemEntity purchaseItem){
		purchaseItemService.updateById(purchaseItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("purchaseitem:purchaseitem:delete")
    public R delete(@RequestBody Long[] ids){
		purchaseItemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
