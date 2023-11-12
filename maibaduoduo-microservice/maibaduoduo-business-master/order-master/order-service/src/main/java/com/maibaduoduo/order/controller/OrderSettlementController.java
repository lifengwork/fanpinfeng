/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maibaduoduo.ordersettlement.entity.OrderSettlementEntity;
import com.maibaduoduo.ordersettlement.service.OrderSettlementService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 订单结算
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 16:41:56
 */
@RestController
@RequestMapping("ordersettlement/ordersettlement")
public class OrderSettlementController {
    @Autowired
    private OrderSettlementService orderSettlementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ordersettlement:ordersettlement:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderSettlementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("ordersettlement:ordersettlement:info")
    public R info(@PathVariable("id") Integer id){
		OrderSettlementEntity orderSettlement = orderSettlementService.getById(id);

        return R.ok().put("orderSettlement", orderSettlement);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("ordersettlement:ordersettlement:save")
    public R save(@RequestBody OrderSettlementEntity orderSettlement){
		orderSettlementService.save(orderSettlement);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("ordersettlement:ordersettlement:update")
    public R update(@RequestBody OrderSettlementEntity orderSettlement){
		orderSettlementService.updateById(orderSettlement);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("ordersettlement:ordersettlement:delete")
    public R delete(@RequestBody Integer[] ids){
		orderSettlementService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
