/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.service.impl;

import com.maibaduoduo.logistics.dao.LogisticsVehicleDao;
import com.maibaduoduo.logistics.entity.LogisticsVehicleEntity;
import com.maibaduoduo.logistics.service.LogisticsVehicleService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;
@Service("logisticsVehicleService")
public class LogisticsVehicleServiceImpl extends ServiceImpl<LogisticsVehicleDao, LogisticsVehicleEntity> implements LogisticsVehicleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogisticsVehicleEntity> page = this.page(
                new Query<LogisticsVehicleEntity>().getPage(params),
                new QueryWrapper<LogisticsVehicleEntity>()
        );

        return new PageUtils(page);
    }

}