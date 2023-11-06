/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.distributor.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maibaduoduo.distributor.entity.DistributorEntity;
import com.maibaduoduo.distributor.service.DistributorService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 分销
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-06-25 09:24:11
 */
@RestController
@RequestMapping("distributor/distributor")
public class DistributorController {
    @Autowired
    private DistributorService distributorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("distributor:distributor:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = distributorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("distributor:distributor:info")
    public R info(@PathVariable("id") Long id){
		DistributorEntity distributor = distributorService.getById(id);

        return R.ok().put("distributor", distributor);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("distributor:distributor:save")
    public R save(@RequestBody DistributorEntity distributor){
		distributorService.save(distributor);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("distributor:distributor:update")
    public R update(@RequestBody DistributorEntity distributor){
		distributorService.updateById(distributor);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("distributor:distributor:delete")
    public R delete(@RequestBody Long[] ids){
		distributorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
