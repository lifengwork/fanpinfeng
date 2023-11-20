/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.financial.statements.dao.AccountsReceivableDao;
import com.maibaduoduo.financial.statements.entity.AccountsReceivableEntity;
import com.maibaduoduo.financial.statements.service.AccountsReceivableService;


@Service("accountsReceivableService")
public class AccountsReceivableServiceImpl extends ServiceImpl<AccountsReceivableDao, AccountsReceivableEntity> implements AccountsReceivableService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountsReceivableEntity> page = this.page(
                new Query<AccountsReceivableEntity>().getPage(params),
                new QueryWrapper<AccountsReceivableEntity>()
        );

        return new PageUtils(page);
    }

}