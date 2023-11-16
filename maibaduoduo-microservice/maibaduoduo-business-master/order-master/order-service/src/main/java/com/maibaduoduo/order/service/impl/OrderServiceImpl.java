/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.purchase.entity.PurchaseItemEntity;
import com.maibaduoduo.store.facade.api.StoreFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.order.dao.OrderDao;
import com.maibaduoduo.order.entity.OrderEntity;
import com.maibaduoduo.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    private StoreFacade storeFacade;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 订单结算
     *
     * @param purchaseItemEntity
     */
    @Override
    public void orderSettlement(PurchaseItemEntity purchaseItemEntity) {
        /**
         * TODO 订单结算
         */
        //备货
        storeFacade.stockUp(JSON.toJSONString(purchaseItemEntity));
    }

}