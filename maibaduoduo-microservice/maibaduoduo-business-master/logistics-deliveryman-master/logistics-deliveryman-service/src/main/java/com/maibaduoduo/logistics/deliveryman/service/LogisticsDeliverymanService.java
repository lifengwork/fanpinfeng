/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.logistics.deliveryman.entity.LogisticsDeliverymanEntity;
import com.maibaduoduo.order.entity.SaasOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 配送员
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-10-25 18:03:06
 */
public interface LogisticsDeliverymanService extends IService<LogisticsDeliverymanEntity> {
    PageUtils queryPage(Map<String, Object> params);
    boolean rushOrderSuccess(SaasOrderEntity orderEntity);
    List<SaasOrderEntity> viewAreaRushOrder(SaasOrderEntity orderEntity);
    int rushOrder(SaasOrderEntity orderEntity);
}

