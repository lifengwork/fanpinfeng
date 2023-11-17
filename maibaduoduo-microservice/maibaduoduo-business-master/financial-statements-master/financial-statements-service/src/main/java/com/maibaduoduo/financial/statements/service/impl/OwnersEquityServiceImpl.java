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

import com.maibaduoduo.financial.statements.dao.OwnersEquityDao;
import com.maibaduoduo.financial.statements.entity.OwnersEquityEntity;
import com.maibaduoduo.financial.statements.service.OwnersEquityService;


@Service("ownersEquityService")
public class OwnersEquityServiceImpl extends ServiceImpl<OwnersEquityDao, OwnersEquityEntity> implements OwnersEquityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OwnersEquityEntity> page = this.page(
                new Query<OwnersEquityEntity>().getPage(params),
                new QueryWrapper<OwnersEquityEntity>()
        );

        return new PageUtils(page);
    }

}