package com.maibaduoduo.common.mq.sender;

import com.maibaduoduo.common.exception.OpException;
import com.maibaduoduo.common.mq.callback.RabbitMQConfirmCallback;
import com.maibaduoduo.common.mq.callback.RabbitMQReturnCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SampleRabbitMqSender extends RabbitSender {
    private RabbitMQConfirmCallback rabbitMQConfirmCallback;
    private RabbitMQReturnCallback rabbitMQReturnCallback;
    private RabbitTemplate rabbitTemplate;

    public SampleRabbitMqSender() {
    }

    public SampleRabbitMqSender(RabbitMQConfirmCallback rabbitMQConfirmCallback,
                                RabbitMQReturnCallback rabbitMQReturnCallback,
                                RabbitTemplate rabbitTemplate) {
        this.rabbitMQConfirmCallback = rabbitMQConfirmCallback;
        this.rabbitMQReturnCallback = rabbitMQReturnCallback;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String exchangeName, String routingKey, Object message) {
        throw new OpException("请实现您的消息发送逻辑");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(rabbitMQConfirmCallback);
        rabbitTemplate.setReturnCallback(rabbitMQReturnCallback);
    }
}
