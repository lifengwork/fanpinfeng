/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.vehicle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.logistics.vehicle.entity.VehicleEntity;

import java.util.Map;

/**
 * 具体交通工具管理表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-14 10:20:32
 */
public interface VehicleService extends IService<VehicleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

