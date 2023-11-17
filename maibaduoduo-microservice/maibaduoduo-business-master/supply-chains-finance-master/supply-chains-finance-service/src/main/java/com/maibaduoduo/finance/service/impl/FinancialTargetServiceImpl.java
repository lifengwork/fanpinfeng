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

import com.maibaduoduo.finance.dao.FinancialTargetDao;
import com.maibaduoduo.finance.entity.FinancialTargetEntity;
import com.maibaduoduo.finance.service.FinancialTargetService;


@Service("financialTargetService")
public class FinancialTargetServiceImpl extends ServiceImpl<FinancialTargetDao, FinancialTargetEntity> implements FinancialTargetService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FinancialTargetEntity> page = this.page(
                new Query<FinancialTargetEntity>().getPage(params),
                new QueryWrapper<FinancialTargetEntity>()
        );

        return new PageUtils(page);
    }

}