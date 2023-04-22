/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.event.listener;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.constants.RabbitConstants;
import com.maibaduoduo.event.EmployeeEvent;
import com.maibaduoduo.mq.sender.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeListener implements ApplicationListener<EmployeeEvent> {
    @Autowired
    private RabbitSender rabbitSender;

    /**
     * TENANT_USER_EXCHANGE
     * @param employeeEvent
     */
    @Override
    public void onApplicationEvent(EmployeeEvent employeeEvent) {
        rabbitSender.sendMessage(RabbitConstants.TENANT_USER_EXCHANGE,RabbitConstants.TENANT_USER_QUEUE,JSON.toJSON(employeeEvent));
    }
}
