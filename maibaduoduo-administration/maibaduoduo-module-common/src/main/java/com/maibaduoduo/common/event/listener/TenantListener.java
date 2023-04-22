package com.maibaduoduo.common.event.listener;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.common.event.TenantEventInfo;
import com.maibaduoduo.common.mq.config.RabbitMQConstant;
import com.maibaduoduo.common.mq.sender.SaasRabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 初始化租户信息
 */
@Component
public class TenantListener implements ApplicationListener<TenantEventInfo> {
    @Autowired
    private SaasRabbitSender saasRabbitSender;
    @Override
    public void onApplicationEvent(TenantEventInfo tenantEvent) {
        saasRabbitSender.sendMessage(RabbitMQConstant.TENANT_INIT_CREATE_EXCHANGE,RabbitMQConstant.TENANT_INIT_CREATE_QUEUE, JSON.toJSON(tenantEvent));
    }
}
