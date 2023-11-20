/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.transport.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.logistics.transport.entity.InlandBolEntity;
import com.maibaduoduo.logistics.transport.service.InlandBolService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-20 10:53:45
 */
@RestController
@RequestMapping("inlandbol/inlandbol")
public class InlandBolController {
    @Autowired
    private InlandBolService inlandBolService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("inlandbol:inlandbol:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = inlandBolService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("inlandbol:inlandbol:info")
    public R info(@PathVariable("id") Long id){
		InlandBolEntity inlandBol = inlandBolService.getById(id);

        return R.ok().put("inlandBol", inlandBol);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("inlandbol:inlandbol:save")
    public R save(@RequestBody InlandBolEntity inlandBol){
		inlandBolService.save(inlandBol);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("inlandbol:inlandbol:update")
    public R update(@RequestBody InlandBolEntity inlandBol){
		inlandBolService.updateById(inlandBol);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("inlandbol:inlandbol:delete")
    public R delete(@RequestBody Long[] ids){
		inlandBolService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
