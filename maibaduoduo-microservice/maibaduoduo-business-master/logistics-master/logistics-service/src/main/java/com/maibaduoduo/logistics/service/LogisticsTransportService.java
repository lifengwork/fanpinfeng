/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.logistics.entity.LogisticsTransportEntity;

import java.util.Map;

/**
 * 运输
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-06-25 09:24:11
 */
public interface LogisticsTransportService extends IService<LogisticsTransportEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

