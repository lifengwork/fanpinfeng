package com.maibaduoduo.common.mq.callback;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>通过实现 ReturnCallback 接口，启动消息失败返回，比如路由不到队列时触发回调</p>
 * <p>用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调。</p>
 */
@Component
public class RabbitMQReturnCallback implements RabbitTemplate.ReturnCallback{
    private Logger logger = LoggerFactory.getLogger(RabbitMQReturnCallback.class);
    /**
     *
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.error("MQ消息入队列失败，replyCode:{}, replyText:{}，exchange:{}，routingKey:{}，消息体:{}", replyCode, replyText, exchange, routingKey, JSON.toJSONString(message.getBody()));
        // TODO 保存消息到数据库
    }
}
