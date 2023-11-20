/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 * <p>
 * SAAS系统设计研发交流
 * <p>
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.finance.mq;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maibaduoduo.config.StaticCount;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class FinancingOrderListener {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(FinancingOrderListener.class);
    private static final String CONTRACT_QUEUE = "";

    private final String PURCHASE_ORDER_FINANCING_QUEUE="";

    @RabbitListener(queues = PURCHASE_ORDER_FINANCING_QUEUE)
    public void process(Channel channel, Message message) throws Exception {
        try {
            JsonNode jsonData = MAPPER.readTree(message.getBody());
            /**
             * 订单信息同步金融机构
             * TODO
             */

            /**
             * 向金融机构申请资金
             * TODO
             */

            /**
             *  创建融资协议
             *  TODO
             */

            this.basicACK(message, channel);
        } catch (Exception e) {
            this.basicNACK(message, channel);
            log.error(ExceptionUtil.stacktraceToString(e));
        }
    }

    /**
     * 正常消费掉后通知mq服务器移除此条mq
     * @param message
     * @param channel
     */
    private void basicACK(Message message, Channel channel) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("=======ack确认===  ----------" + message.getMessageProperties().getDeliveryTag() + "总投递量：" + StaticCount.atomicInteger.incrementAndGet());
        } catch (IOException e) {
            log.error("通知服务器移除mq时异常，异常信息：" + e);
        }
    }

    /**
     * 处理异常，mq重回队列
     * @param message
     * @param channel
     */
    private void basicNACK(Message message, Channel channel) {
        try {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            log.info("=======Nack确认----------");
        } catch (IOException e) {
            log.error("重新进入服务器时出现异常，异常信息：" + e);
        }
    }

    /**
     * 处理异常，消息拒绝
     * @param message
     * @param channel
     */
    private void basicReject(Message message, Channel channel) {
        try {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("消息拒绝抛出异常：" + e);
        }
    }
}
