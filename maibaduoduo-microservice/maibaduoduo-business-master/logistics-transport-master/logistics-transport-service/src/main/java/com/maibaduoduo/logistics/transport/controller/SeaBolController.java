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

import com.maibaduoduo.logistics.transport.entity.SeaBolEntity;
import com.maibaduoduo.logistics.transport.service.SeaBolService;
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
@RequestMapping("seabol/seabol")
public class SeaBolController {
    @Autowired
    private SeaBolService seaBolService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seabol:seabol:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seaBolService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("seabol:seabol:info")
    public R info(@PathVariable("id") Long id){
		SeaBolEntity seaBol = seaBolService.getById(id);

        return R.ok().put("seaBol", seaBol);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("seabol:seabol:save")
    public R save(@RequestBody SeaBolEntity seaBol){
		seaBolService.save(seaBol);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("seabol:seabol:update")
    public R update(@RequestBody SeaBolEntity seaBol){
		seaBolService.updateById(seaBol);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("seabol:seabol:delete")
    public R delete(@RequestBody Long[] ids){
		seaBolService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
