/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.retailer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.order.entity.OrderEntity;
import com.maibaduoduo.retailer.entity.RetailerEntity;
import com.maibaduoduo.store.entity.GoodsEntity;

import java.util.Map;

/**
 * 零售
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-06-25 09:24:11
 */
public interface RetailerService extends IService<RetailerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 设置加急订单
     * @param orderEntity
     * @return
     */
    boolean rushOrder(OrderEntity orderEntity);
    /**
     * 采购物品
     */
    void doPurchase(GoodsEntity goodsEntity);
}

