/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.service.impl;

import com.maibaduoduo.store.dao.FreightChargeDao;
import com.maibaduoduo.store.entity.FreightChargeEntity;
import com.maibaduoduo.store.service.FreightChargeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

@Service("freightChargeService")
public class FreightChargeServiceImpl extends ServiceImpl<FreightChargeDao, FreightChargeEntity> implements FreightChargeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FreightChargeEntity> page = this.page(
                new Query<FreightChargeEntity>().getPage(params),
                new QueryWrapper<FreightChargeEntity>()
        );

        return new PageUtils(page);
    }

}