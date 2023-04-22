package com.maibaduoduo.common.mq.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by Administrator on 2019/11/1 0001.
 */
public class RecvHandlerFanout implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(RecvHandlerFanout.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public void onMessage(Message msg) {
        try {
            // msg就是rabbitmq传来的消息，需要的同学自己打印看一眼
            // 使用jackson解析
            JsonNode jsonData = MAPPER.readTree(msg.getBody());
            logger.info("测试RabbitMq：" + jsonData.get("id_fanout").asText() + ",Fanout发布模式：" + jsonData.get("name_fanout").asText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
