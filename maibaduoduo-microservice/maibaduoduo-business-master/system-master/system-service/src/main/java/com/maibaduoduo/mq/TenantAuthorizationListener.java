/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.mq;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maibaduoduo.common.utils.RedisUtils;
import com.maibaduoduo.config.StaticCount;
import com.maibaduoduo.constants.RabbitConstants;
import com.maibaduoduo.database.datasource.dynamic.TenantInfo;
import com.maibaduoduo.database.datasource.utils.BeanUtils;
import com.maibaduoduo.database.datasource.utils.DynamicDataSourceContextHolder;
import com.maibaduoduo.service.SysUserRoleService;
import com.maibaduoduo.service.SysUserService;
import com.maibaduoduo.sys.entity.SysUserEntity;
import com.maibaduoduo.sys.entity.SysUserRoleEntity;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

/**
 * 对租户授权登录业务端系统
 */
@Component
public class TenantAuthorizationListener {
    private final Logger logger = LoggerFactory.getLogger(TenantCreateListener.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private RedisUtils redisUtils;
    /**
     * TENANT_DB_URL_MASTER214506583172841472
     * @param channel
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = RabbitConstants.TENANT_AUTHORIZATION_QUEUE)
    public void process(Channel channel, Message message) throws Exception {
        try {
            logger.info("[{}]处理库存队列消息，消息体：{},{}", RabbitConstants.TENANT_AUTHORIZATION_QUEUE,new String(message.getBody()));
            JsonNode jsonData = MAPPER.readTree(message.getBody());
            String tenantId = jsonData.get("tenantId").asText();
            TenantInfo tenantInfo = BeanUtils.stringToBean(jedisPool.getResource().get("TENANT_DB_URL_MASTER"+tenantId), TenantInfo.class);
            DynamicDataSourceContextHolder.setDataSourceKey(tenantInfo.getDbName());
            SysUserEntity sysUserEntity = new SysUserEntity();
            sysUserEntity.setStatus(0);
            sysUserEntity.setUsername(jsonData.get("employeeId").asText());
            SysUserEntity sysUserEntityByUserId = sysUserService.getOne(new Wrapper<SysUserEntity>() {
                @Override
                public SysUserEntity getEntity() {
                    return sysUserEntity;
                }

                @Override
                public MergeSegments getExpression() {
                    return null;
                }

                @Override
                public void clear() {

                }

                @Override
                public String getSqlSegment() {
                    return null;
                }
            });
            SysUserEntity toCacheUser = new SysUserEntity();
            toCacheUser.setUserId(sysUserEntityByUserId.getUserId());
            toCacheUser.setTenantId(sysUserEntityByUserId.getTenantId());
            toCacheUser.setUsername(sysUserEntityByUserId.getUsername());//PC端登录
            toCacheUser.setMobile(sysUserEntityByUserId.getMobile());//手机号用来APP端登录
            //TODO
            if(StringUtils.isNotEmpty(sysUserEntityByUserId.getUsername())){
                redisUtils.set(sysUserEntityByUserId.getUsername(), toCacheUser);
            }
            if(StringUtils.isNotEmpty(sysUserEntityByUserId.getMobile())){
                redisUtils.set(sysUserEntityByUserId.getMobile(), toCacheUser);
            }
            SysUserEntity sysUserEntityUpdate = new SysUserEntity();
            sysUserEntityUpdate.setStatus(jsonData.get("status").asInt());
            sysUserEntityUpdate.setUserId(sysUserEntityByUserId.getUserId());
            sysUserService.update(sysUserEntityUpdate);
            SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
            sysUserRoleEntity.setRoleId(1l);
            sysUserRoleEntity.setUserId(sysUserEntityByUserId.getUserId());

            sysUserRoleService.save(sysUserRoleEntity);
            basicACK(message,channel);
        } catch (Exception e) {
            logger.error("MQ消息处理异常，消息体:{},{}", message.getMessageProperties().getCorrelationId(), e);
            basicNACK(message,channel);
        }
    }
    private void basicACK(Message message, Channel channel) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            logger.info("=======ack确认===  ----------"+ message.getMessageProperties().getDeliveryTag()+"总投递量："+ StaticCount.atomicInteger.incrementAndGet());
        } catch (IOException e) {
            logger.error("通知服务器移除mq时异常，异常信息：" + e);
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
            logger.info("=======Nack确认----------");
        } catch (IOException e) {
            logger.error("重新进入服务器时出现异常，异常信息：" + e);
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
            logger.error("消息拒绝抛出异常：" + e);
        }
    }
}
