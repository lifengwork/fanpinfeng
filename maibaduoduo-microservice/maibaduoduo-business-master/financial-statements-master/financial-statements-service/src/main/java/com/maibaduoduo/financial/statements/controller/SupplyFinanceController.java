/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.controller;

import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.financial.statements.entity.SupplyFinanceVo;
import com.maibaduoduo.financial.statements.service.SupplyFinanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 供应商财务情况统计
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:12:24
 */
@RestController
@RequestMapping("finance/supply")
public class SupplyFinanceController {
    @Autowired
    private SupplyFinanceService supplyFinanceService;

    /**
     * 供应商财务情况统计
     * 数据来源：
     *    企业ERP系统
     *    企业财务报表
     */
    @GetMapping("/{id}")
    @RequiresPermissions("supply:finance:info")
    public R info(@PathVariable("id") String id) {
        SupplyFinanceVo supplyFinance = supplyFinanceService.computeSupplyFinance(id);
        return R.ok().put("supplyFinance", supplyFinance);
    }

}
