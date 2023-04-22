package com.maibaduoduo.common.mq.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by Administrator on 2019/11/1 0001.
 */
public class RecvHandlerDirect implements MessageListener {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * RabbitMQ消息监听程序异常时，消费者会向rabbitmq server发送Basic.Reject，表示消息拒绝接受，
     * 由于Spring默认requeue-rejected配置为true，消息会重新入队，然后rabbitmq server重新投递，造成了程序一直异常的情况，
     * 程序一定要添加try…catch语句!!!当然你也可以根据实际情况，
     * 选择设置requeue-rejected为false来丢弃消息。
     * @param msg
     */
    public void onMessage(Message msg) {
        try {
            // msg就是rabbitmq传来的消息
            // 使用jackson解析
            JsonNode jsonData = MAPPER.readTree(msg.getBody());
            System.out.println("测试RabbitMq：" + jsonData.get("id_direct").asText() + ",Direct发布模式：" + jsonData.get("name_direct").asText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
