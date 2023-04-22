package com.maibaduoduo.config;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.mq.CustomCorrelationData;
import com.maibaduoduo.mq.sender.RabbitSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQConfirmCallback implements RabbitTemplate.ConfirmCallback{
    /**
     * 系统配置
     */
    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private RabbitSender rabbitSender;
    /**
     * 用于实现消息发送到RabbitMQ交换器后接收ack回调。
     * 如果消息发送确认失败就进行重试。
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 消息回调确认失败处理
        if (!ack) {
            CustomCorrelationData correlationDataExtends = (CustomCorrelationData)correlationData;
            //消息发送失败,就进行重试，重试过后还不能成功就记录到数据库
            if (correlationDataExtends.getRetryCount() < systemConfig.getMqRetryCount()) {
                log.info("MQ消息发送失败，消息重发，消息ID：{}，重发次数：{}，消息体:{}", correlationDataExtends.getId(), correlationDataExtends.getRetryCount(), JSON.toJSONString(correlationDataExtends.getMessage()));

                // 将重试次数加一
                correlationDataExtends.setRetryCount(correlationDataExtends.getRetryCount() + 1);

                // 重发发消息
                rabbitSender.convertAndSend(correlationDataExtends.getExchange(), correlationDataExtends.getRoutingKey(), correlationDataExtends.getMessage(), correlationDataExtends);

            } else {
                //消息重试发送失败,将消息放到数据库等待补发
                log.warn("MQ消息重发失败，消息入库，消息ID：{}，消息体:{}", correlationData.getId(), JSON.toJSONString(correlationDataExtends.getMessage()));

                // TODO 保存消息到数据库
            }
        } else {
            log.info("消息发送成功,消息ID:{}", correlationData.getId());
        }
    }
}
