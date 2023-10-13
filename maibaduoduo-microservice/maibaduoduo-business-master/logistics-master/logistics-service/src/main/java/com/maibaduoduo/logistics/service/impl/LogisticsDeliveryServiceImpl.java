/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.service.impl;

import com.maibaduoduo.logistics.dao.LogisticsDeliveryDao;
import com.maibaduoduo.logistics.entity.LogisticsDeliveryEntity;
import com.maibaduoduo.logistics.service.LogisticsDeliveryService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

@Service("logisticsDeliveryService")
public class LogisticsDeliveryServiceImpl extends ServiceImpl<LogisticsDeliveryDao, LogisticsDeliveryEntity> implements LogisticsDeliveryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogisticsDeliveryEntity> page = this.page(
                new Query<LogisticsDeliveryEntity>().getPage(params),
                new QueryWrapper<LogisticsDeliveryEntity>()
        );

        return new PageUtils(page);
    }

}