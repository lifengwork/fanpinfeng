/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.maibaduoduo.configuration.utils.*;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramTask;
import com.maibaduoduo.logistics.deliveryman.task.program.EventData;
import com.maibaduoduo.logistics.deliveryman.task.program.ExecuteObject;
import com.maibaduoduo.logistics.deliveryman.task.publisher.RushOrderEventPublisher;
import com.maibaduoduo.order.entity.SaasOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.maibaduoduo.logistics.deliveryman.dao.LogisticsDeliverymanDao;
import com.maibaduoduo.logistics.deliveryman.entity.LogisticsDeliverymanEntity;
import com.maibaduoduo.logistics.deliveryman.service.LogisticsDeliverymanService;

import static com.maibaduoduo.jwt.common.BaseContextHandler.getUserId;


@Service("logisticsDeliverymanService")
public class LogisticsDeliverymanServiceImpl extends ServiceImpl<LogisticsDeliverymanDao, LogisticsDeliverymanEntity> implements LogisticsDeliverymanService {
    @Autowired
    private RushOrderEventPublisher rushOrderEventPublisher;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
     *
     * @param orderEntity
     * @return
     */
    @Override
    public boolean rushOrderSuccess(SaasOrderEntity orderEntity) {
        rushOrderEventPublisher.publishEvent(new ProgramTask()
                .setExecuteObject(new ExecuteObject().
                        setEventData(new EventData().
                                setContent(JSON.toJSONString(orderEntity)))
                        .setExecuteId(IdGen.SnowFlakeLong())), 0);
        return false;
    }

    /**
     * 加急订单被抢之后从缓存中删除
     * 并刷新前端页面
     *
     * @param orderEntity
     * @return
     */
    @Override
    public int rushOrder(SaasOrderEntity orderEntity) {
        String cacheKey = String.valueOf(orderEntity.getId());
        if (redisUtils.setIfAbsent(cacheKey, "1")) {
            redisUtils.delete(orderEntity.getOrderNo());
            this.rushOrderSuccess(orderEntity);
            return 1;
        }
        return 0;
    }

    /**
     * 展示配送员所在区域的加急订单
     *
     * @param orderEntity
     * @return
     */
    @Override
    public List<SaasOrderEntity> viewAreaRushOrder(SaasOrderEntity orderEntity) {
        List<SaasOrderEntity> saasOrderEntities = Lists.newArrayList();
        String areaCode = "10000000";//配送员所在区域代码
        List<Object> objectList = redisTemplate.boundHashOps(areaCode).values();
        /**
         * TODO
         * 解析并转换成SaasOrderEntity
         */
        return saasOrderEntities;
    }
}