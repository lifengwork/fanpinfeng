/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.purchase.service.impl;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.configuration.exception.SaasException;
import com.maibaduoduo.event.OrderEvent;
import com.maibaduoduo.purchase.entity.PurchaseItemEntity;
import com.maibaduoduo.purchase.service.PurchaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.purchase.dao.PurchaseDao;
import com.maibaduoduo.purchase.entity.PurchaseEntity;
import com.maibaduoduo.purchase.service.PurchaseService;
import org.springframework.transaction.annotation.Transactional;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Autowired
    private PurchaseItemService purchaseItemService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = SaasException.class)
    public boolean save(PurchaseEntity entity) {
        //创建采购单
        super.save(entity);
        PurchaseItemEntity purchaseItemEntity = new PurchaseItemEntity();
        //保存具体采购物品数据
        boolean isOk = purchaseItemService.save(purchaseItemEntity);
        applicationContext.publishEvent(new OrderEvent(JSON.toJSON(purchaseItemEntity)));
        return isOk;
    }

}