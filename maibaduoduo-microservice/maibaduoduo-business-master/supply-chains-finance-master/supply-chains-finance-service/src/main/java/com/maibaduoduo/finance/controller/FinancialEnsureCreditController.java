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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maibaduoduo.finance.entity.FinancialEnsureCreditEntity;
import com.maibaduoduo.finance.service.FinancialEnsureCreditService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 担保人征信
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:01:07
 */
@RestController
@RequestMapping("financialensurecredit/financialensurecredit")
public class FinancialEnsureCreditController {
    @Autowired
    private FinancialEnsureCreditService financialEnsureCreditService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("financialensurecredit:financialensurecredit:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financialEnsureCreditService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("financialensurecredit:financialensurecredit:info")
    public R info(@PathVariable("id") Long id){
		FinancialEnsureCreditEntity financialEnsureCredit = financialEnsureCreditService.getById(id);

        return R.ok().put("financialEnsureCredit", financialEnsureCredit);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("financialensurecredit:financialensurecredit:save")
    public R save(@RequestBody FinancialEnsureCreditEntity financialEnsureCredit){
		financialEnsureCreditService.save(financialEnsureCredit);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("financialensurecredit:financialensurecredit:update")
    public R update(@RequestBody FinancialEnsureCreditEntity financialEnsureCredit){
		financialEnsureCreditService.updateById(financialEnsureCredit);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("financialensurecredit:financialensurecredit:delete")
    public R delete(@RequestBody Long[] ids){
		financialEnsureCreditService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
