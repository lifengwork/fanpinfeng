/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.vehicle.service.impl;

import com.maibaduoduo.logistics.vehicle.dao.VehicleDao;
import com.maibaduoduo.logistics.vehicle.entity.VehicleEntity;
import com.maibaduoduo.logistics.vehicle.service.VehicleService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;


@Service("vehicleService")
public class VehicleServiceImpl extends ServiceImpl<VehicleDao, VehicleEntity> implements VehicleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VehicleEntity> page = this.page(
                new Query<VehicleEntity>().getPage(params),
                new QueryWrapper<VehicleEntity>()
        );

        return new PageUtils(page);
    }

}