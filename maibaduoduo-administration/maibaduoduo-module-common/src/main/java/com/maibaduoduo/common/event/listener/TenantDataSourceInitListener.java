package com.maibaduoduo.common.event.listener;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.common.event.TenantInitDataSourceInfo;
import com.maibaduoduo.common.mq.config.RabbitMQConstant;
import com.maibaduoduo.common.mq.sender.SaasRabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 数据源初始化
 */
@Component
public class TenantDataSourceInitListener implements ApplicationListener<TenantInitDataSourceInfo> {

    @Autowired
    private SaasRabbitSender saasRabbitSender;

    @Override
    public void onApplicationEvent(TenantInitDataSourceInfo tenantInitDataSourceInfo) {
        saasRabbitSender.sendMessage(RabbitMQConstant.TENANT_DATASOURCE_INIT_EXCHANGE,RabbitMQConstant.TENANT_DATASOURCE_INIT_QUEUE, JSON.toJSON(tenantInitDataSourceInfo));
    }
}
