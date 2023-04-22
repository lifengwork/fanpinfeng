package com.maibaduoduo.common.mq.sender;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.common.mq.callback.RabbitMQConfirmCallback;
import com.maibaduoduo.common.mq.callback.RabbitMQReturnCallback;
import com.maibaduoduo.common.mq.config.CustomCorrelationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
/**
 * Rabbit 发送MQ消息
 * @author admin
 */
@Component
public class SaasRabbitSender extends SampleRabbitMqSender {
    private final Logger logger = LoggerFactory.getLogger(SaasRabbitSender.class);

    private RabbitTemplate rabbitTemplate;

    public SaasRabbitSender(RabbitMQConfirmCallback rabbitMQConfirmCallback, RabbitMQReturnCallback rabbitMQReturnCallback, RabbitTemplate rabbitTemplate) {
        super(rabbitMQConfirmCallback, rabbitMQReturnCallback, rabbitTemplate);
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送MQ消息
     *
     * @param exchangeName 交换机名称
     * @param routingKey   路由名称
     * @param message      发送消息体
     */
    public void sendMessage(String exchangeName, String routingKey, Object message) {
        CustomCorrelationData correlationData = this.correlationData(message);
        correlationData.setExchange(exchangeName);
        correlationData.setRoutingKey(routingKey);
        correlationData.setMessage(message);
        logger.info("发送MQ消息，消息ID：{}，消息体:{}, exchangeName:{}, routingKey:{}", correlationData.getId(), JSON.toJSONString(message), exchangeName, routingKey);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message, correlationData);
    }

}
