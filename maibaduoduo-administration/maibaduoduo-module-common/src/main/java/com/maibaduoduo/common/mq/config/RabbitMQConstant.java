package com.maibaduoduo.common.mq.config;

/**
 * 消息队列常量类
 */
public abstract class RabbitMQConstant {
    public static final String TENANT_INIT_CREATE_EXCHANGE= "tenant.create.exchange";
    public static final String TENANT_INIT_CREATE_QUEUE = "tenant.create.queue";
    public static final String TENANT_USER_QUEUE = "tenant.user.queue";
    public static final String TENANT_USER_EXCHANGE = "tenant.user.exchange";
    public static final String TENANT_AUTHORIZATION_QUEUE = "tenant.authorization.queue";
    public static final String TENANT_AUTHORIZATION_EXCHANGE = "tenant.authorization.exchange";
    public static final String TENANT_DATASOURCE_INIT_EXCHANGE="tenant_datasource_init_exchange";
    public static final String TENANT_DATASOURCE_INIT_QUEUE="tenant_datasource_init_queue";
}
