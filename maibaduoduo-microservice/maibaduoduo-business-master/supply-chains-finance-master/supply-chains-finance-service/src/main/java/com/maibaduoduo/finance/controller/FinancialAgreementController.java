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

import com.maibaduoduo.finance.entity.FinancialAgreementEntity;
import com.maibaduoduo.finance.service.FinancialAgreementService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 融资协议
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:01:07
 */
@RestController
@RequestMapping("financialagreement/financialagreement")
public class FinancialAgreementController {
    @Autowired
    private FinancialAgreementService financialAgreementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("financialagreement:financialagreement:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financialAgreementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("financialagreement:financialagreement:info")
    public R info(@PathVariable("id") Long id){
		FinancialAgreementEntity financialAgreement = financialAgreementService.getById(id);

        return R.ok().put("financialAgreement", financialAgreement);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("financialagreement:financialagreement:save")
    public R save(@RequestBody FinancialAgreementEntity financialAgreement){
		financialAgreementService.save(financialAgreement);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("financialagreement:financialagreement:update")
    public R update(@RequestBody FinancialAgreementEntity financialAgreement){
		financialAgreementService.updateById(financialAgreement);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("financialagreement:financialagreement:delete")
    public R delete(@RequestBody Long[] ids){
		financialAgreementService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
