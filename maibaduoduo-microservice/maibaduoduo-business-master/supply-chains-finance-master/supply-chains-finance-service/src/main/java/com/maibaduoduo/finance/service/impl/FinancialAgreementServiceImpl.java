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

import com.maibaduoduo.finance.dao.FinancialAgreementDao;
import com.maibaduoduo.finance.entity.FinancialAgreementEntity;
import com.maibaduoduo.finance.service.FinancialAgreementService;


@Service("financialAgreementService")
public class FinancialAgreementServiceImpl extends ServiceImpl<FinancialAgreementDao, FinancialAgreementEntity> implements FinancialAgreementService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FinancialAgreementEntity> page = this.page(
                new Query<FinancialAgreementEntity>().getPage(params),
                new QueryWrapper<FinancialAgreementEntity>()
        );

        return new PageUtils(page);
    }

}