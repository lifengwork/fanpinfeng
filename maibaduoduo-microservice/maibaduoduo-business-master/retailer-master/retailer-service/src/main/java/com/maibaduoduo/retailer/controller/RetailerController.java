/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.retailer.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.retailer.entity.RetailerEntity;
import com.maibaduoduo.retailer.service.RetailerService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 零售
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-06-25 09:24:11
 */
@RestController
@RequestMapping("retailer/retailer")
public class RetailerController {
    @Autowired
    private RetailerService retailerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("retailer:retailer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = retailerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("retailer:retailer:info")
    public R info(@PathVariable("id") Long id){
		RetailerEntity retailer = retailerService.getById(id);

        return R.ok().put("retailer", retailer);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("retailer:retailer:save")
    public R save(@RequestBody RetailerEntity retailer){
		retailerService.save(retailer);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("retailer:retailer:update")
    public R update(@RequestBody RetailerEntity retailer){
		retailerService.updateById(retailer);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("retailer:retailer:delete")
    public R delete(@RequestBody Long[] ids){
		retailerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
