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

import com.maibaduoduo.finance.dao.FinancialInstitutionDao;
import com.maibaduoduo.finance.entity.FinancialInstitutionEntity;
import com.maibaduoduo.finance.service.FinancialInstitutionService;


@Service("financialInstitutionService")
public class FinancialInstitutionServiceImpl extends ServiceImpl<FinancialInstitutionDao, FinancialInstitutionEntity> implements FinancialInstitutionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FinancialInstitutionEntity> page = this.page(
                new Query<FinancialInstitutionEntity>().getPage(params),
                new QueryWrapper<FinancialInstitutionEntity>()
        );

        return new PageUtils(page);
    }

}