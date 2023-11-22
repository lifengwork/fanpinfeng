/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.service.impl;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.mq.sender.RabbitSender;
import com.maibaduoduo.store.dao.StoreInventoryDao;
import com.maibaduoduo.store.entity.StoreInventoryEntity;
import com.maibaduoduo.store.service.StoreInventoryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

@Service("storeInventoryService")
public class StoreInventoryServiceImpl extends ServiceImpl<StoreInventoryDao, StoreInventoryEntity> implements StoreInventoryService {
    @Autowired
    private RabbitSender rabbitSender;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StoreInventoryEntity> page = this.page(
                new Query<StoreInventoryEntity>().getPage(params),
                new QueryWrapper<StoreInventoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * RDC库存同步
     * @param storeInventoryEntity
     */
    private void synchInventory(StoreInventoryEntity storeInventoryEntity) {
        /**
         * TODO
         */
        String STORE_INVENTORY_EXCHANGE ="";
        String STORE_INVENTORY_QUEUE = "";
        rabbitSender.sendMessage(STORE_INVENTORY_EXCHANGE,STORE_INVENTORY_QUEUE, JSON.toJSON(storeInventoryEntity));
    }
}