package com.maibaduoduo.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQReturnCallback implements RabbitTemplate.ReturnCallback{

    /**
     * 用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调。
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("MQ消息入队列失败，replyCode:{}, replyText:{}，exchange:{}，routingKey:{}，消息体:{}", replyCode, replyText, exchange, routingKey, JSON.toJSONString(message.getBody()));

        // TODO 保存消息到数据库
    }
}
