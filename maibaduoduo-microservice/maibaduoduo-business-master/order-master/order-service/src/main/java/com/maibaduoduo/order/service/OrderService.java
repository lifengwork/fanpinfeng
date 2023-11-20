/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.order.entity.OrderEntity;
import com.maibaduoduo.purchase.entity.PurchaseEntity;
import com.maibaduoduo.purchase.entity.PurchaseItemEntity;

import java.util.Map;

/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 16:43:29
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 订单结算
     * @param purchaseEntity
     */
    void orderSettlement(PurchaseEntity purchaseEntity);

    /**
     * 创建采购订单
     * @param orderEntity
     */
    void createPurchaseOrder(OrderEntity orderEntity);

    /**
     * 金融机构信用核实通过-提供资金后回调
     */
    void callBack(String callBackInfo);
}

