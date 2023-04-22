/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.mq;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.config.StaticCount;
import com.maibaduoduo.constants.RabbitConstants;
import com.maibaduoduo.database.datasource.dynamic.DynamicDataSource;
import com.maibaduoduo.database.datasource.dynamic.TenantInfo;
import com.maibaduoduo.database.datasource.utils.BeanUtils;
import com.maibaduoduo.database.datasource.utils.DynamicDataSourceContextHolder;
import com.maibaduoduo.service.SysUserService;
import com.maibaduoduo.sys.entity.SysUserEntity;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 处理消息
 * 在业务端创建租户登录账号
 * @author admin
 */
@Slf4j
@Service
public class TenantCreateListener {

    private final Logger logger = LoggerFactory.getLogger(TenantCreateListener.class);
    private AtomicInteger delayTime=new AtomicInteger(10);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private DynamicDataSource dynamicDataSource;
    @RabbitListener(queues = RabbitConstants.TENANT_INIT_CREATE_QUEUE)
    public void process(Channel channel, Message message) throws Exception {
        try {
        //数据库在创建中时会出现错误，需要延迟执行
        while(delayTime.decrementAndGet()>0){
            TimeUnit.SECONDS.sleep(2);
              logger.info("租户数据库创建中..........");
        }
        Map<String,Object> resultMap= ((Map)JSON.parse(new String(message.getBody())));//
        String tenantId = String.valueOf(resultMap.get("tenantId"));

        Set<String> sysTenantDbUrlList =jedisPool.getResource().keys("TENANT_DB_URL_MASTER*");
        TenantInfo tenantInfo =null;
        for(String tenant:sysTenantDbUrlList){
            if(tenant.endsWith(tenantId)){
                tenantInfo = BeanUtils.stringToBean(jedisPool.getResource().get(tenant),TenantInfo.class);
                dynamicDataSource.addTenantTargetDataSources(tenantInfo.getDbName(),tenantInfo.getDbUrl());
                DynamicDataSourceContextHolder.setDataSourceKey(tenantInfo.getDbName());
                break;
            }
        }
        TimeUnit.SECONDS.sleep(10);
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setCreateTime(new Date());
        sysUserEntity.setPassword("123456");
        sysUserEntity.setStatus(0);
        sysUserEntity.setTenantId(String.valueOf(resultMap.get("tenantId")));
        sysUserEntity.setUsername(String.valueOf(resultMap.get("tenantId")));
        sysUserService.saveUserFromSaas(sysUserEntity);

        // 确认消息已经消费成功
         basicACK(message,channel);
        } catch (Exception e) {
            logger.error("MQ消息处理异常，消息体:{},{}", message.getMessageProperties().getCorrelationId(), e);
            basicNACK(message,channel);
        }
    }

    /**
     * 正常消费掉后通知mq服务器移除此条mq
     * @param message
     * @param channel
     */
    private void basicACK(Message message, Channel channel) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("=======ack确认===  ----------"+ message.getMessageProperties().getDeliveryTag()+"总投递量："+ StaticCount.atomicInteger.incrementAndGet());
        } catch (IOException e) {
            log.error("通知服务器移除mq时异常，异常信息：" + e);
        }
    }

    /**
     * 处理异常，mq重回队列
     * @param message
     * @param channel
     */
    private void basicNACK(Message message, Channel channel) {
        try {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            log.info("=======Nack确认----------");
        } catch (IOException e) {
            log.error("重新进入服务器时出现异常，异常信息：" + e);
        }
    }

    /**
     * 处理异常，消息拒绝
     * @param message
     * @param channel
     */
    private void basicReject(Message message, Channel channel) {
        try {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("消息拒绝抛出异常：" + e);
        }
    }
}