/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.logistics.dao.LogisticsDeliverymanDao;
import com.maibaduoduo.logistics.entity.LogisticsDeliverymanEntity;
import com.maibaduoduo.logistics.service.LogisticsDeliverymanService;


@Service("logisticsDeliverymanService")
public class LogisticsDeliverymanServiceImpl extends ServiceImpl<LogisticsDeliverymanDao, LogisticsDeliverymanEntity> implements LogisticsDeliverymanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogisticsDeliverymanEntity> page = this.page(
                new Query<LogisticsDeliverymanEntity>().getPage(params),
                new QueryWrapper<LogisticsDeliverymanEntity>()
        );

        return new PageUtils(page);
    }

}