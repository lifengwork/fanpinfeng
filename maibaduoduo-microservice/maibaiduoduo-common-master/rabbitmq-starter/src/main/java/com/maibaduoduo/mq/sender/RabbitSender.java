package com.maibaduoduo.mq.sender;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.config.RabbitMQConfirmCallback;
import com.maibaduoduo.config.RabbitMQReturnCallback;
import com.maibaduoduo.mq.CustomCorrelationData;
import com.maibaduoduo.config.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * Rabbit 发送消息
 *
 * @author saas
 */
@Service
public class RabbitSender implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    /**
     * Rabbit MQ 客户端
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConfirmCallback rabbitMQConfirmCallback;
    @Autowired
    private RabbitMQReturnCallback rabbitMQReturnCallback;

    /**
     * 系统配置
     */
    @Autowired
    private SystemConfig systemConfig;

    /**
     * 发送MQ消息
     *
     * @param exchangeName 交换机名称
     * @param routingKey   路由名称
     * @param message      发送消息体
     */
    public void sendMessage(String exchangeName, String routingKey, Object message) {
        Assert.notNull(message, "message 消息体不能为NULL");
        Assert.notNull(exchangeName, "exchangeName 不能为NULL");
        Assert.notNull(routingKey, "routingKey 不能为NULL");

        // 获取CorrelationData对象
        CustomCorrelationData correlationData = this.correlationData(message);
        correlationData.setExchange(exchangeName);
        correlationData.setRoutingKey(routingKey);
        correlationData.setMessage(message);

        logger.info("发送MQ消息，消息ID：{}，消息体:{}, exchangeName:{}, routingKey:{}", correlationData.getId(), JSON.toJSONString(message), exchangeName, routingKey);
        // 发送消息
        this.convertAndSend(exchangeName, routingKey, message, correlationData);
    }


    /**
     * 消息相关数据（消息ID）
     *
     * @param message
     * @return
     */
    private CustomCorrelationData correlationData(Object message) {

        return new CustomCorrelationData(UUID.randomUUID().toString(), message);
    }

    /**
     * 发送消息
     *
     * @param exchange        交换机名称
     * @param routingKey      路由key
     * @param message         消息内容
     * @param correlationData 消息相关数据（消息ID）
     * @throws AmqpException
     */
    public void convertAndSend(String exchange, String routingKey, final Object message, CustomCorrelationData correlationData) throws AmqpException {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
        } catch (Exception e) {
            logger.error("MQ消息发送异常，消息ID：{}，消息体:{}, exchangeName:{}, routingKey:{}", correlationData.getId(), JSON.toJSONString(message), exchange, routingKey, e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(rabbitMQConfirmCallback);
        rabbitTemplate.setReturnCallback(rabbitMQReturnCallback);
    }

}
