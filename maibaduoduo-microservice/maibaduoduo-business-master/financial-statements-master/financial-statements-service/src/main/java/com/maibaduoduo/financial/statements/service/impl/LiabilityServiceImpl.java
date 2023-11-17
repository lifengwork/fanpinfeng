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

import com.maibaduoduo.financial.statements.dao.LiabilityDao;
import com.maibaduoduo.financial.statements.entity.LiabilityEntity;
import com.maibaduoduo.financial.statements.service.LiabilityService;


@Service("liabilityService")
public class LiabilityServiceImpl extends ServiceImpl<LiabilityDao, LiabilityEntity> implements LiabilityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LiabilityEntity> page = this.page(
                new Query<LiabilityEntity>().getPage(params),
                new QueryWrapper<LiabilityEntity>()
        );

        return new PageUtils(page);
    }

}