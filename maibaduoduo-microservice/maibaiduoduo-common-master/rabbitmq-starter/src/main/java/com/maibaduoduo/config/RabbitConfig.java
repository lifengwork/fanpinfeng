package com.maibaduoduo.config;

import com.maibaduoduo.constants.RabbitConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ 配置类
 *
 * @author saas
 */
@Configuration
public class RabbitConfig {

    @Autowired
    RabbitProperties properties;

    /**
     * 方法rabbitAdmin的功能描述:动态声明queue、exchange、routing
     *
     * @param connectionFactory
     * @return
     * @author : saas
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setIgnoreDeclarationExceptions(true);
        //声明delay队列（Fanout类型的exchange）
        //延迟delay交换机
        FanoutExchange delayExchange = new FanoutExchange(RabbitConstants.MQ_EXCHANGE_DELAY_QUEUE);
        rabbitAdmin.declareExchange(delayExchange);
        Queue delayQueue = new Queue(RabbitConstants.QUEUE_NAME_DELAY_QUEUE);
        rabbitAdmin.declareQueue(delayQueue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(delayQueue).to(delayExchange));

        //开通用户
        DirectExchange authorizationExchange= new DirectExchange(RabbitConstants.TENANT_AUTHORIZATION_EXCHANGE);
        rabbitAdmin.declareExchange(authorizationExchange);
        Queue couponQueue = queue(RabbitConstants.TENANT_AUTHORIZATION_QUEUE);
        rabbitAdmin.declareQueue(couponQueue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(couponQueue).to(authorizationExchange).with(RabbitConstants.TENANT_AUTHORIZATION_QUEUE));

        //初始化租户数据源
        DirectExchange initDataExchange = new DirectExchange(RabbitConstants.TENANT_DATASOURCE_INIT_EXCHANGE);
        rabbitAdmin.declareExchange(initDataExchange);
        Queue dataSourceIintQueue = queue(RabbitConstants.TENANT_DATASOURCE_INIT_QUEUE);
        rabbitAdmin.declareQueue(dataSourceIintQueue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(dataSourceIintQueue).to(initDataExchange).with(RabbitConstants.TENANT_DATASOURCE_INIT_QUEUE));

        //创建租户用户
        DirectExchange initUserExchange = new DirectExchange(RabbitConstants.TENANT_USER_EXCHANGE);
        rabbitAdmin.declareExchange(initUserExchange);
        Queue initUserQueue = queue(RabbitConstants.TENANT_USER_QUEUE);
        rabbitAdmin.declareBinding(BindingBuilder.bind(initUserQueue).to(initUserExchange).with(RabbitConstants.TENANT_USER_QUEUE));

        //初始化租户在业务系统的登录账号
        DirectExchange initCreateTenantAccountExchange = new DirectExchange(RabbitConstants.TENANT_INIT_CREATE_EXCHANGE);
        rabbitAdmin.declareExchange(initCreateTenantAccountExchange);
        Queue initCreateTenantAccountQueue = queue(RabbitConstants.TENANT_INIT_CREATE_QUEUE);
        rabbitAdmin.declareQueue(initCreateTenantAccountQueue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(initCreateTenantAccountQueue).to(initCreateTenantAccountExchange).with(RabbitConstants.TENANT_INIT_CREATE_QUEUE));
        return rabbitAdmin;
    }

    public Queue queue(String name) {
        Map<String, Object> args = new HashMap<>();
        // 设置死信队列
        args.put("x-dead-letter-exchange", RabbitConstants.MQ_EXCHANGE_DEAD_QUEUE);
        args.put("x-dead-letter-routing-key", RabbitConstants.MQ_ROUTING_KEY_DEAD_QUEUE);
        // 设置消息的过期时间， 单位是毫秒
        args.put("x-message-ttl", 5000);

        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue(name, durable, exclusive, autoDelete, args);
    }

    /**
     * 消息格式
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Value("${mq.retry.count:3}")
    private int mqRetryCount;

    public int getMqRetryCount() {
        return mqRetryCount;
    }

    public void setMqRetryCount(int mqRetryCount) {
        this.mqRetryCount = mqRetryCount;
    }
}
