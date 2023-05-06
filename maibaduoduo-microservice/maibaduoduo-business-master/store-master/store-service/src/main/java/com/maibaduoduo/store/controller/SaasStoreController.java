/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.controller;

import com.maibaduoduo.store.service.SaasStoreService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.store.entity.SaasStoreEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @date 2023-04-16 13:00:02
 */
@RestController
@RequestMapping("/saas/store")
@Api("仓库接口文档")
public class SaasStoreController {
    @Autowired
    private SaasStoreService saasStoreService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("generator:saasstore:list")
    @ApiOperation("仓库列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = saasStoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("generator:saasstore:info")
    @ApiOperation("仓库信息")
    public R info(@PathVariable("id") Long id) throws Exception {
		SaasStoreEntity saasStore = saasStoreService.getById(id);
        return R.ok().put("saasStore", saasStore);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("generator:saasstore:save")
    @ApiOperation("仓库保存")
    public R save(@RequestBody SaasStoreEntity saasStore){
		saasStoreService.save(saasStore);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("generator:saasstore:update")
    @ApiOperation("仓库修改")
    public R update(@RequestBody SaasStoreEntity saasStore){
		saasStoreService.updateById(saasStore);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("generator:saasstore:delete")
    @ApiOperation("仓库删除")
    public R delete(@RequestBody Long[] ids){
		saasStoreService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
