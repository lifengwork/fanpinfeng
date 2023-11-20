/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.payment.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.payment.entity.PurchasePaymentEntity;
import com.maibaduoduo.payment.service.PurchasePaymentService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-20 20:56:57
 */
@RestController
@RequestMapping("purchasepayment/purchasepayment")
public class PurchasePaymentController {
    @Autowired
    private PurchasePaymentService purchasePaymentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("purchasepayment:purchasepayment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchasePaymentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("purchasepayment:purchasepayment:info")
    public R info(@PathVariable("id") Long id){
		PurchasePaymentEntity purchasePayment = purchasePaymentService.getById(id);

        return R.ok().put("purchasePayment", purchasePayment);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("purchasepayment:purchasepayment:save")
    public R save(@RequestBody PurchasePaymentEntity purchasePayment){
		purchasePaymentService.save(purchasePayment);

        return R.ok();
    }

    /**
     * 保存
     */
    @PostMapping("/pay")
    public R pay(@RequestBody PurchasePaymentEntity purchasePayment){
        purchasePaymentService.pay(purchasePayment);

        return R.ok();
    }

    /**
     * 采购方付款银行回调接口
     */
    @PostMapping("/callback")
    public R callback(@RequestBody String callBackInfo){
        purchasePaymentService.callback(callBackInfo);

        return R.ok();
    }

    /**
     * 偿还所融资金本息回调
     */
    @PostMapping("/debt/callback")
    public R debtCallback(@RequestBody String debtcallBackInfo){
        purchasePaymentService.debtCallBack(debtcallBackInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("purchasepayment:purchasepayment:update")
    public R update(@RequestBody PurchasePaymentEntity purchasePayment){
		purchasePaymentService.updateById(purchasePayment);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("purchasepayment:purchasepayment:delete")
    public R delete(@RequestBody Long[] ids){
		purchasePaymentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
