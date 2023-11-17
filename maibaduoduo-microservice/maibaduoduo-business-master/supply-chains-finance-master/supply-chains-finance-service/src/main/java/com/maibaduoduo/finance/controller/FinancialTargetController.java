/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.finance.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.finance.entity.FinancialTargetEntity;
import com.maibaduoduo.finance.service.FinancialTargetService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 融资单表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:01:07
 */
@RestController
@RequestMapping("financialtarget/financialtarget")
public class FinancialTargetController {
    @Autowired
    private FinancialTargetService financialTargetService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("financialtarget:financialtarget:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financialTargetService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("financialtarget:financialtarget:info")
    public R info(@PathVariable("id") Long id){
		FinancialTargetEntity financialTarget = financialTargetService.getById(id);

        return R.ok().put("financialTarget", financialTarget);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("financialtarget:financialtarget:save")
    public R save(@RequestBody FinancialTargetEntity financialTarget){
		financialTargetService.save(financialTarget);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("financialtarget:financialtarget:update")
    public R update(@RequestBody FinancialTargetEntity financialTarget){
		financialTargetService.updateById(financialTarget);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("financialtarget:financialtarget:delete")
    public R delete(@RequestBody Long[] ids){
		financialTargetService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
