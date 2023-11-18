/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.scp.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.scp.dao.ScpFinancialDao;
import com.maibaduoduo.scp.entity.ScpFinancialEntity;
import com.maibaduoduo.scp.service.ScpFinancialService;


@Service("scpFinancialService")
public class ScpFinancialServiceImpl extends ServiceImpl<ScpFinancialDao, ScpFinancialEntity> implements ScpFinancialService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ScpFinancialEntity> page = this.page(
                new Query<ScpFinancialEntity>().getPage(params),
                new QueryWrapper<ScpFinancialEntity>()
        );

        return new PageUtils(page);
    }

}