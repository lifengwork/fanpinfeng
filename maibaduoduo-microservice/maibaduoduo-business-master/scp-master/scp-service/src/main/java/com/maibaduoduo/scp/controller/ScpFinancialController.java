/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.scp.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.scp.entity.ScpFinancialEntity;
import com.maibaduoduo.scp.service.ScpFinancialService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-18 22:34:39
 */
@RestController
@RequestMapping("scpfinancial/scpfinancial")
public class ScpFinancialController {
    @Autowired
    private ScpFinancialService scpFinancialService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("scpfinancial:scpfinancial:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scpFinancialService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("scpfinancial:scpfinancial:info")
    public R info(@PathVariable("id") Long id){
		ScpFinancialEntity scpFinancial = scpFinancialService.getById(id);

        return R.ok().put("scpFinancial", scpFinancial);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("scpfinancial:scpfinancial:save")
    public R save(@RequestBody ScpFinancialEntity scpFinancial){
		scpFinancialService.save(scpFinancial);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("scpfinancial:scpfinancial:update")
    public R update(@RequestBody ScpFinancialEntity scpFinancial){
		scpFinancialService.updateById(scpFinancial);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("scpfinancial:scpfinancial:delete")
    public R delete(@RequestBody Long[] ids){
		scpFinancialService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
