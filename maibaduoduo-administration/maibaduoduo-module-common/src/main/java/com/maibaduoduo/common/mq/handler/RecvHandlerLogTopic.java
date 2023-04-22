package com.maibaduoduo.common.mq.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.apache.avalon.framework.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import java.io.IOException;

/**
 * Created by Administrator on 2019/11/1 0001.
 */
public class RecvHandlerLogTopic implements ChannelAwareMessageListener {
    private static Logger log = LoggerFactory.getLogger(RecvHandlerLogTopic.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            // msg就是rabbitmq传来的消息，需要的同学自己打印看一眼
            // 使用jackson解析
            JsonNode jsonData = MAPPER.readTree(message.getBody());
            System.out.println("Log==测试RabbitMq：" + jsonData.get("id_topic").asText() + ",Topic发布模式：" + jsonData.get("name_topic").asText());
            /**
             * to do 消息处理 成功或者失败
             */
           if(true)
               basicACK(message,channel);
            else
               basicNACK(message,channel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //正常消费掉后通知mq服务器移除此条mq
    private void basicACK(Message message,Channel channel){
        try{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch(IOException e){
            log.error("通知服务器移除mq时异常，异常信息："+ ExceptionUtil.printStackTrace(e));
        }
    }
    //处理异常，mq重回队列
    private void basicNACK(Message message,Channel channel) {
        try {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        } catch (IOException e) {
            log.error("重新进入服务器时出现异常，异常信息：" + ExceptionUtil.printStackTrace(e));
        }
    }

}
