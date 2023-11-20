/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maibaduoduo.accountsreceivable.entity.AccountsReceivableEntity;
import com.maibaduoduo.accountsreceivable.service.AccountsReceivableService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.R;



/**
 * 应收账款表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-20 10:53:45
 */
@RestController
@RequestMapping("accountsreceivable/accountsreceivable")
public class AccountsReceivableController {
    @Autowired
    private AccountsReceivableService accountsReceivableService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("accountsreceivable:accountsreceivable:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = accountsReceivableService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("accountsreceivable:accountsreceivable:info")
    public R info(@PathVariable("id") Long id){
		AccountsReceivableEntity accountsReceivable = accountsReceivableService.getById(id);

        return R.ok().put("accountsReceivable", accountsReceivable);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("accountsreceivable:accountsreceivable:save")
    public R save(@RequestBody AccountsReceivableEntity accountsReceivable){
		accountsReceivableService.save(accountsReceivable);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("accountsreceivable:accountsreceivable:update")
    public R update(@RequestBody AccountsReceivableEntity accountsReceivable){
		accountsReceivableService.updateById(accountsReceivable);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("accountsreceivable:accountsreceivable:delete")
    public R delete(@RequestBody Long[] ids){
		accountsReceivableService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
