/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.financial.statements.entity.IncomeEntity;
import com.maibaduoduo.financial.statements.service.IncomeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 利润表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:12:24
 */
@RestController
@RequestMapping("income/income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("income:income:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = incomeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("income:income:info")
    public R info(@PathVariable("id") Long id){
		IncomeEntity income = incomeService.getById(id);

        return R.ok().put("income", income);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("income:income:save")
    public R save(@RequestBody IncomeEntity income){
		incomeService.save(income);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("income:income:update")
    public R update(@RequestBody IncomeEntity income){
		incomeService.updateById(income);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("income:income:delete")
    public R delete(@RequestBody Long[] ids){
		incomeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
