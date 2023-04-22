package com.maibaduoduo.constants;

/**
 * 消息队列常量
 *
 * @author saas
 * @Description
 * @return
 * @Date 2017/7/5 15:42
 */
public final class RabbitConstants {
    public static final String MQ_EXCHANGE_DEAD_QUEUE = "test-dead-queue-exchange";
    public static final String QUEUE_NAME_DELAY_QUEUE = "test-delay-queue";
    public static final String MQ_EXCHANGE_DELAY_QUEUE = "test-delay-queue-exchange";
    public static final String MQ_ROUTING_KEY_DEAD_QUEUE = "test-routing-key-dead-queue";
    /**
     * 租户账号创建
     * topic_exchange","tenant.topic"
     */
    public static final String TENANT_INIT_CREATE_EXCHANGE= "tenant.create.exchange";
    public static final String TENANT_INIT_CREATE_QUEUE = "tenant.create.queue";
    public static final String TENANT_USER_QUEUE = "tenant.user.queue";
    public static final String TENANT_USER_EXCHANGE = "tenant.user.exchange";
    public static final String TENANT_AUTHORIZATION_QUEUE = "tenant.authorization.queue";
    public static final String TENANT_AUTHORIZATION_EXCHANGE = "tenant.authorization.exchange";
    public static final String TENANT_DATASOURCE_INIT_EXCHANGE="tenant_datasource_init_exchange";
    public static final String TENANT_DATASOURCE_INIT_QUEUE="tenant_datasource_init_queue";
}
