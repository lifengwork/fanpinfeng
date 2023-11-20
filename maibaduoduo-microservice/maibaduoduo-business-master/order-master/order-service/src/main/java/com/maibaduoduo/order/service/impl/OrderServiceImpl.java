/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.mq.sender.RabbitSender;
import com.maibaduoduo.purchase.entity.PurchaseEntity;
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

    @Autowired
    private RabbitSender rabbitSender;
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
     * @param purchaseEntity
     */
    @Override
    public void orderSettlement(PurchaseEntity purchaseEntity) {
        /**
         * TODO 订单结算
         */
        //备货
        storeFacade.stockUp(JSON.toJSONString(purchaseEntity));
    }

    @Override
    public void createPurchaseOrder(OrderEntity orderEntity) {
        /**
         * TODO 生成采购订单
         */
    }

    @Override
    public void callBack(String callBackInfo) {
        /**
         * 征信异常融资业务办理失败
         * 记录明细
         * 返回
         * TODO
         */
         if(true){
             /**
              * 记录明细
              * 备货
              * TODO
              */
             String CONTRACT_STORE_EXCHANGE = "";
             String CONTRACT_STORE_QUEUE = "";
             OrderEntity orderEntity = new OrderEntity();
             rabbitSender.sendMessage(CONTRACT_STORE_EXCHANGE,CONTRACT_STORE_QUEUE, JSON.toJSON(orderEntity));
         }else{
             //征信不通过记录明细
         }
    }
}