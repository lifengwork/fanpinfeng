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

import com.maibaduoduo.finance.entity.FinancialInstitutionEntity;
import com.maibaduoduo.finance.service.FinancialInstitutionService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 金融机构表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:01:07
 */
@RestController
@RequestMapping("financialinstitution/financialinstitution")
public class FinancialInstitutionController {
    @Autowired
    private FinancialInstitutionService financialInstitutionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("financialinstitution:financialinstitution:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financialInstitutionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("financialinstitution:financialinstitution:info")
    public R info(@PathVariable("id") Long id){
		FinancialInstitutionEntity financialInstitution = financialInstitutionService.getById(id);

        return R.ok().put("financialInstitution", financialInstitution);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("financialinstitution:financialinstitution:save")
    public R save(@RequestBody FinancialInstitutionEntity financialInstitution){
		financialInstitutionService.save(financialInstitution);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("financialinstitution:financialinstitution:update")
    public R update(@RequestBody FinancialInstitutionEntity financialInstitution){
		financialInstitutionService.updateById(financialInstitution);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("financialinstitution:financialinstitution:delete")
    public R delete(@RequestBody Long[] ids){
		financialInstitutionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
