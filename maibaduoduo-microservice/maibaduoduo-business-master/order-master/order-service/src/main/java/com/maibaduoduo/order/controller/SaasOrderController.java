/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.controller;

import com.maibaduoduo.order.service.SaasOrderService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.order.entity.SaasOrderEntity;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author lf
 * @email lifengwork@yeah.net
 * @date 2023-04-16 13:00:03
 */
@RestController
@RequestMapping("/order")
@Api("订单接口文档")
public class SaasOrderController {
    @Autowired
    private SaasOrderService saasOrderService;

    @RequestMapping("/ribbon")
    public R testRibbon(){
        return R.ok("ribbon");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = saasOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:info")
    public R info(@PathVariable("id") Long id){
		SaasOrderEntity saasOrder = saasOrderService.getById(id);

        return R.ok().put("saasOrder", saasOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:save")
    public R save(@RequestBody SaasOrderEntity saasOrder){
		saasOrderService.save(saasOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:update")
    public R update(@RequestBody SaasOrderEntity saasOrder){
		saasOrderService.updateById(saasOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:delete")
    public R delete(@RequestBody Long[] ids){
		saasOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
