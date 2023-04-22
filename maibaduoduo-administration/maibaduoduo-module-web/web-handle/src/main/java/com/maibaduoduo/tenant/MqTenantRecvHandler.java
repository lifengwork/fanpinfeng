/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.tenant;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maibaduoduo.base.tenant.entity.TenantEmployeeInfo;
import com.maibaduoduo.base.tenant.service.TenantEmployeeInfoService;
import com.maibaduoduo.common.utils.SpringContextHolder;
import com.rabbitmq.client.Channel;
import org.apache.avalon.framework.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import java.io.IOException;

/**
 * 租户在业务端创建用户绑定到租户
 */
public class MqTenantRecvHandler implements ChannelAwareMessageListener {
    private static Logger log = LoggerFactory.getLogger(MqTenantRecvHandler.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private TenantEmployeeInfoService tenantEmployeeInfoService= (TenantEmployeeInfoService)SpringContextHolder.getBean("tenantEmployeeInfoService");
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            // 使用jackson解析
            JsonNode jsonData = MAPPER.readTree(message.getBody());
            TenantEmployeeInfo tenantEmployeeInfo = new TenantEmployeeInfo();
            tenantEmployeeInfo.setIsNewRecord(true);
            tenantEmployeeInfo.setTenantEmployeeId(jsonData.get("employeeId").asText());
            tenantEmployeeInfo.setTenantEmployeeName(jsonData.get("employeeName").asText());
            tenantEmployeeInfo.setTenantId(jsonData.get("tenantId").asText());
            tenantEmployeeInfo.setTenantName(jsonData.get("tenantName").asText());
            tenantEmployeeInfo.setStatus(0);
            tenantEmployeeInfoService.save(tenantEmployeeInfo);
            basicACK(message, channel);
        } catch (Exception e) {
            basicNACK(message,channel);
            log.error(ExceptionUtil.printStackTrace(e));
        }
    }

    /**
     * 正常消费掉后通知mq服务器移除此条mq
     * @param message
     * @param channel
     */
    private void basicACK(Message message,Channel channel){
        try{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch(IOException e){
            log.error("通知服务器移除mq时异常，异常信息："+ ExceptionUtil.printStackTrace(e));
        }
    }

    /**
     * 处理异常，mq重回队列
     * @param message
     * @param channel
     */
    private void basicNACK(Message message,Channel channel) {
        try {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        } catch (IOException e) {
            log.error("重新进入服务器时出现异常，异常信息：" + ExceptionUtil.printStackTrace(e));
        }
    }
}
