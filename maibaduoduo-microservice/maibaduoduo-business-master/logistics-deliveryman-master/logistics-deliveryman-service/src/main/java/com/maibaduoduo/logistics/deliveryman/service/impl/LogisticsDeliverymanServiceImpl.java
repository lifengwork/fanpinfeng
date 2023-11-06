/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.service.impl;

import com.maibaduoduo.logistics.deliveryman.task.event.ProgramTask;
import com.maibaduoduo.logistics.deliveryman.task.program.ExecuteObject;
import com.maibaduoduo.logistics.deliveryman.task.publisher.RushOrderEventPublisher;
import com.maibaduoduo.order.entity.SaasOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.logistics.deliveryman.dao.LogisticsDeliverymanDao;
import com.maibaduoduo.logistics.deliveryman.entity.LogisticsDeliverymanEntity;
import com.maibaduoduo.logistics.deliveryman.service.LogisticsDeliverymanService;


@Service("logisticsDeliverymanService")
public class LogisticsDeliverymanServiceImpl extends ServiceImpl<LogisticsDeliverymanDao, LogisticsDeliverymanEntity> implements LogisticsDeliverymanService {
    @Autowired
    private RushOrderEventPublisher rushOrderEventPublisher;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogisticsDeliverymanEntity> page = this.page(
                new Query<LogisticsDeliverymanEntity>().getPage(params),
                new QueryWrapper<LogisticsDeliverymanEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 配送员抢单成功
     * @param orderEntity
     * @return
     */
    @Override
    public boolean rushOrder(SaasOrderEntity orderEntity) {
        ProgramTask programTask = new ProgramTask();
        ExecuteObject executeObject = new ExecuteObject();
        executeObject.setSaasOrderEntity(orderEntity);
        programTask.setExecuteObject(executeObject);
        rushOrderEventPublisher.publishEvent(programTask,0);
        return false;
    }

}