/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.store.entity.GoodsSnEntity;
import com.maibaduoduo.store.service.GoodsSnService;
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
@RequestMapping("goodssn/goodssn")
public class GoodsSnController {
    @Autowired
    private GoodsSnService goodsSnService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodssn:goodssn:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsSnService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("goodssn:goodssn:info")
    public R info(@PathVariable("id") Long id){
		GoodsSnEntity goodsSn = goodsSnService.getById(id);

        return R.ok().put("goodsSn", goodsSn);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("goodssn:goodssn:save")
    public R save(@RequestBody GoodsSnEntity goodsSn){
		goodsSnService.save(goodsSn);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("goodssn:goodssn:update")
    public R update(@RequestBody GoodsSnEntity goodsSn){
		goodsSnService.updateById(goodsSn);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("goodssn:goodssn:delete")
    public R delete(@RequestBody Long[] ids){
		goodsSnService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
