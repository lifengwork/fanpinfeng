/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.retailer.service.impl;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.configuration.utils.IdGen;
import com.maibaduoduo.order.entity.OrderEntity;
import com.maibaduoduo.purchase.entity.PurchaseEntity;
import com.maibaduoduo.purchase.facade.api.PurchaseFacade;
import com.maibaduoduo.store.entity.GoodsEntity;
import com.maibaduoduo.task.event.ProgramTask;
import com.maibaduoduo.task.program.EventData;
import com.maibaduoduo.task.program.ExecuteObject;
import com.maibaduoduo.task.publisher.OrderEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.retailer.dao.RetailerDao;
import com.maibaduoduo.retailer.entity.RetailerEntity;
import com.maibaduoduo.retailer.service.RetailerService;


@Service("retailerService")
public class RetailerServiceImpl extends ServiceImpl<RetailerDao, RetailerEntity> implements RetailerService {

    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Autowired
    private PurchaseFacade purchaseFacade;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RetailerEntity> page = this.page(
                new Query<RetailerEntity>().getPage(params),
                new QueryWrapper<RetailerEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 设置为加急订单
     */
    public boolean rushOrder(OrderEntity orderEntity){
        orderEventPublisher.publishEvent(new ProgramTask()
                .setExecuteObject(new ExecuteObject()
                        .setEventData(new EventData()
                                .setContent(JSON.toJSONString(orderEntity),orderEntity.getShopAddrCode()))
                        .setExecuteId(IdGen.SnowFlakeLong())), 0);
        return true;
    }

    @Override
    public void doPurchase(GoodsEntity goodsEntity) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseFacade.doPurchase(purchaseEntity);

    }

}