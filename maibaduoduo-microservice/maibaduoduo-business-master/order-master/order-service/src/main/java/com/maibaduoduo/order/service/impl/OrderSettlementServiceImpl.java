/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.service.impl;

import com.maibaduoduo.order.dao.OrderSettlementDao;
import com.maibaduoduo.order.entity.OrderSettlementEntity;
import com.maibaduoduo.order.service.OrderSettlementService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

@Service("orderSettlementService")
public class OrderSettlementServiceImpl extends ServiceImpl<OrderSettlementDao, OrderSettlementEntity> implements OrderSettlementService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderSettlementEntity> page = this.page(
                new Query<OrderSettlementEntity>().getPage(params),
                new QueryWrapper<OrderSettlementEntity>()
        );

        return new PageUtils(page);
    }

}