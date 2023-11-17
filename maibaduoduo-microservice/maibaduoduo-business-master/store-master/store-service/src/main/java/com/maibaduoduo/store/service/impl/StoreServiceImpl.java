/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.service.impl;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.configuration.utils.IdGen;
import com.maibaduoduo.purchase.entity.PurchaseItemEntity;
import com.maibaduoduo.store.task.publisher.StockUpEventPublisher;
import com.maibaduoduo.task.event.ProgramTask;
import com.maibaduoduo.task.program.EventData;
import com.maibaduoduo.task.program.ExecuteObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.store.dao.StoreDao;
import com.maibaduoduo.store.entity.StoreEntity;
import com.maibaduoduo.store.service.StoreService;


@Service("storeService")
public class StoreServiceImpl extends ServiceImpl<StoreDao, StoreEntity> implements StoreService {
@Autowired
private StockUpEventPublisher stockUpEventPublisher;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StoreEntity> page = this.page(
                new Query<StoreEntity>().getPage(params),
                new QueryWrapper<StoreEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void stockUp(PurchaseItemEntity purchaseItemEntity) {
        /**
         * TODO
         * 1选择就近仓库
         * 2选择运输方式
         * 3选择最有路线
         * 4计算运费
         */
        stockUpEventPublisher.publishEvent(new ProgramTask()
                .setExecuteObject(new ExecuteObject()
                        .setEventData(new EventData()
                                .setContent(JSON.toJSONString(purchaseItemEntity)))
                        .setExecuteId(IdGen.SnowFlakeLong())), 0);
    }

}