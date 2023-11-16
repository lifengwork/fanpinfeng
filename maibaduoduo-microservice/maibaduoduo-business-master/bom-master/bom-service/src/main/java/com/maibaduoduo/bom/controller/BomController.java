/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.bom.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.bom.entity.BomEntity;
import com.maibaduoduo.bom.service.BomService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 产品物料清单
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-14 12:06:49
 */
@RestController
@RequestMapping("bom/bom")
public class BomController {
    @Autowired
    private BomService bomService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bom:bom:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bomService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("bom:bom:info")
    public R info(@PathVariable("id") Long id){
		BomEntity bom = bomService.getById(id);

        return R.ok().put("bom", bom);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("bom:bom:save")
    public R save(@RequestBody BomEntity bom){
		bomService.save(bom);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("bom:bom:update")
    public R update(@RequestBody BomEntity bom){
		bomService.updateById(bom);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("bom:bom:delete")
    public R delete(@RequestBody Long[] ids){
		bomService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
