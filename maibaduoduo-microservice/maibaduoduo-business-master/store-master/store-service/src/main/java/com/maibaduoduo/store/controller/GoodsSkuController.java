/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.store.entity.GoodsSkuEntity;
import com.maibaduoduo.store.service.GoodsSkuService;
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
 * @date 2023-11-12 18:04:34
 */
@RestController
@RequestMapping("goodssku/goodssku")
public class GoodsSkuController {
    @Autowired
    private GoodsSkuService goodsSkuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodssku:goodssku:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("goodssku:goodssku:info")
    public R info(@PathVariable("id") Long id){
		GoodsSkuEntity goodsSku = goodsSkuService.getById(id);

        return R.ok().put("goodsSku", goodsSku);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("goodssku:goodssku:save")
    public R save(@RequestBody GoodsSkuEntity goodsSku){
		goodsSkuService.save(goodsSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("goodssku:goodssku:update")
    public R update(@RequestBody GoodsSkuEntity goodsSku){
		goodsSkuService.updateById(goodsSku);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("goodssku:goodssku:delete")
    public R delete(@RequestBody Long[] ids){
		goodsSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
