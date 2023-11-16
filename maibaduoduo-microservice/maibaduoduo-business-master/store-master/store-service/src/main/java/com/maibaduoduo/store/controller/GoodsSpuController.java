/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.store.entity.GoodsSpuEntity;
import com.maibaduoduo.store.service.GoodsSpuService;
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
@RequestMapping("goodsspu/goodsspu")
public class GoodsSpuController {
    @Autowired
    private GoodsSpuService goodsSpuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodsspu:goodsspu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsSpuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("goodsspu:goodsspu:info")
    public R info(@PathVariable("id") Long id){
		GoodsSpuEntity goodsSpu = goodsSpuService.getById(id);

        return R.ok().put("goodsSpu", goodsSpu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("goodsspu:goodsspu:save")
    public R save(@RequestBody GoodsSpuEntity goodsSpu){
		goodsSpuService.save(goodsSpu);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("goodsspu:goodsspu:update")
    public R update(@RequestBody GoodsSpuEntity goodsSpu){
		goodsSpuService.updateById(goodsSpu);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("goodsspu:goodsspu:delete")
    public R delete(@RequestBody Long[] ids){
		goodsSpuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
