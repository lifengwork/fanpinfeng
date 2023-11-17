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

import com.maibaduoduo.financial.statements.dao.AssetsDao;
import com.maibaduoduo.financial.statements.entity.AssetsEntity;
import com.maibaduoduo.financial.statements.service.AssetsService;


@Service("assetsService")
public class AssetsServiceImpl extends ServiceImpl<AssetsDao, AssetsEntity> implements AssetsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AssetsEntity> page = this.page(
                new Query<AssetsEntity>().getPage(params),
                new QueryWrapper<AssetsEntity>()
        );

        return new PageUtils(page);
    }

}