/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.finance.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.finance.dao.FinancialEnsureCreditDao;
import com.maibaduoduo.finance.entity.FinancialEnsureCreditEntity;
import com.maibaduoduo.finance.service.FinancialEnsureCreditService;


@Service("financialEnsureCreditService")
public class FinancialEnsureCreditServiceImpl extends ServiceImpl<FinancialEnsureCreditDao, FinancialEnsureCreditEntity> implements FinancialEnsureCreditService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FinancialEnsureCreditEntity> page = this.page(
                new Query<FinancialEnsureCreditEntity>().getPage(params),
                new QueryWrapper<FinancialEnsureCreditEntity>()
        );

        return new PageUtils(page);
    }

}