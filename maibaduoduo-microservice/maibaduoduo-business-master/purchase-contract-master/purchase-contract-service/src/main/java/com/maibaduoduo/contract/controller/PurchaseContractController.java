/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.contract.controller;

import java.util.Arrays;
import java.util.Map;

import com.maibaduoduo.contract.entity.PurchaseContractEntity;
import com.maibaduoduo.contract.service.PurchaseContractService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-13 21:59:29
 */
@RestController
@RequestMapping("purchasecontract/purchasecontract")
public class PurchaseContractController {
    @Autowired
    private PurchaseContractService purchaseContractService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("purchasecontract:purchasecontract:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseContractService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("purchasecontract:purchasecontract:info")
    public R info(@PathVariable("id") Long id){
		PurchaseContractEntity purchaseContract = purchaseContractService.getById(id);

        return R.ok().put("purchaseContract", purchaseContract);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("purchasecontract:purchasecontract:save")
    public R save(@RequestBody PurchaseContractEntity purchaseContract){
		purchaseContractService.save(purchaseContract);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("purchasecontract:purchasecontract:update")
    public R update(@RequestBody PurchaseContractEntity purchaseContract){
		purchaseContractService.updateById(purchaseContract);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("purchasecontract:purchasecontract:delete")
    public R delete(@RequestBody Long[] ids){
		purchaseContractService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
