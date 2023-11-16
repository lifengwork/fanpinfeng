/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.order.entity.OrderItemEntity;
import java.util.Map;

/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 16:41:56
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

