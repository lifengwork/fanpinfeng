package com.maibaduoduo.common.event.listener;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.common.event.TenantAuthorizationInfo;
import com.maibaduoduo.common.mq.config.RabbitMQConstant;
import com.maibaduoduo.common.mq.sender.SaasRabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 业务用户含租户端登录授权
 */
@Component
public class AuthorizationListener implements ApplicationListener<TenantAuthorizationInfo> {

    @Autowired
    private SaasRabbitSender saasRabbitSender;

    @Override
    public void onApplicationEvent(TenantAuthorizationInfo tenantAuthorizationInfo) {
        saasRabbitSender.sendMessage(RabbitMQConstant.TENANT_AUTHORIZATION_EXCHANGE,RabbitMQConstant.TENANT_AUTHORIZATION_QUEUE, JSON.toJSON(tenantAuthorizationInfo));
    }
}
