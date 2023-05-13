/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.supplier.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.supplier.entity.SupplierEntity;
import com.maibaduoduo.supplier.service.SupplierService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-05-13 21:00:36
 */
@RestController
@RequestMapping("supplier/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("supplier:supplier:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = supplierService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("supplier:supplier:info")
    public R info(@PathVariable("id") Long id){
		SupplierEntity supplier = supplierService.getById(id);

        return R.ok().put("supplier", supplier);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("supplier:supplier:save")
    public R save(@RequestBody SupplierEntity supplier){
		supplierService.save(supplier);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("supplier:supplier:update")
    public R update(@RequestBody SupplierEntity supplier){
		supplierService.updateById(supplier);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("supplier:supplier:delete")
    public R delete(@RequestBody Long[] ids){
		supplierService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
