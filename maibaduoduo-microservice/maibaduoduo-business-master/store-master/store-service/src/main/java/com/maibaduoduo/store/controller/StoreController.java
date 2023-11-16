/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.controller;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.purchase.entity.PurchaseItemEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.store.entity.StoreEntity;
import com.maibaduoduo.store.service.StoreService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 仓储表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
@RestController
@RequestMapping("store/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("store:store:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = storeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("store:store:info")
    public R info(@PathVariable("id") Long id){
		StoreEntity store = storeService.getById(id);

        return R.ok().put("store", store);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("store:store:save")
    public R save(@RequestBody StoreEntity store){
		storeService.save(store);

        return R.ok();
    }

    /**
     * 备货
     */
    @PostMapping("/stockup")
    @RequiresPermissions("store:store:save")
    public R stockUp(@RequestBody String stockup){
        PurchaseItemEntity purchaseItemEntity = (PurchaseItemEntity) JSON.parseArray(stockup, PurchaseItemEntity.class);
        storeService.stockUp(purchaseItemEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("store:store:update")
    public R update(@RequestBody StoreEntity store){
		storeService.updateById(store);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("store:store:delete")
    public R delete(@RequestBody Long[] ids){
		storeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
