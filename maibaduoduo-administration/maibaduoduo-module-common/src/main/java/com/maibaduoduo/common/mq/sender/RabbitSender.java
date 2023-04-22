package com.maibaduoduo.common.mq.sender;

import org.springframework.beans.factory.InitializingBean;

public abstract class RabbitSender implements Sender, InitializingBean {
    public abstract void sendMessage(String exchangeName, String routingKey, Object message);
}
