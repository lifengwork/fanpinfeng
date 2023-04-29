/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.app.controller;

import com.maibaduoduo.app.service.SaasOrderStoreService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.configuration.utils.ValidatorUtils;
import com.maibaduoduo.order.entity.PassWordEntity;
import com.maibaduoduo.order.entity.SaasOrderStoreEntity;
import com.maibaduoduo.store.entity.SaasStoreEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
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
@RequestMapping("/saas/orderstore")
public class SaasOrderStoreController {
    @Autowired
    private SaasOrderStoreService saasOrderStoreService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:saasorderstore:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = saasOrderStoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:saasorderstore:info")
    public R info(@PathVariable("id") Long id){
		//SaasOrderStoreEntity saasOrderStore = saasOrderStoreService.getById(id);
        SaasStoreEntity saasStoreEntity = saasOrderStoreService.querySassStoreEntity(id);
        return R.ok().put("saasStoreEntity", saasStoreEntity);
    }

    /**
     * 信息
     */
    @RequestMapping("/validator")
    @RequiresPermissions("generator:saasorderstore:info")
    public R infovalidator(@RequestBody PassWordEntity passWordEntity){
        ValidatorUtils.validateEntity(passWordEntity);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:saasorderstore:save")
    public R save(@RequestBody SaasOrderStoreEntity saasOrderStore){
		saasOrderStoreService.save(saasOrderStore);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:saasorderstore:update")
    public R update(@RequestBody SaasOrderStoreEntity saasOrderStore){
		saasOrderStoreService.updateById(saasOrderStore);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:saasorderstore:delete")
    public R delete(@RequestBody Long[] ids){
		saasOrderStoreService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
