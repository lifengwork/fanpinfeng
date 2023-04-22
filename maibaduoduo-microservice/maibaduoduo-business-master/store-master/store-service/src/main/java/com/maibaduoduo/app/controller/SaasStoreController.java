/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.app.controller;

import com.maibaduoduo.app.service.SaasStoreService;
import com.maibaduoduo.common.utils.PageUtils;
import com.maibaduoduo.common.utils.R;
import com.maibaduoduo.store.entity.SaasStoreEntity;
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
public class SaasStoreController {
    @Autowired
    private SaasStoreService saasStoreService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:saasstore:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = saasStoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:saasstore:info")
    public R info(@PathVariable("id") Long id){
		SaasStoreEntity saasStore = saasStoreService.getById(id);

        return R.ok().put("saasStore", saasStore);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:saasstore:save")
    public R save(@RequestBody SaasStoreEntity saasStore){
		saasStoreService.save(saasStore);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:saasstore:update")
    public R update(@RequestBody SaasStoreEntity saasStore){
		saasStoreService.updateById(saasStore);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:saasstore:delete")
    public R delete(@RequestBody Long[] ids){
		saasStoreService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
