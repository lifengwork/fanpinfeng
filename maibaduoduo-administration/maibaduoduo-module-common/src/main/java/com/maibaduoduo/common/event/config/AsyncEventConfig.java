package com.maibaduoduo.common.event.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步事件线程池配置
 * 默认不开启
 */
@EnableAsync
@Conditional(CustomizeCondition.class)
@Component
public class AsyncEventConfig implements AsyncConfigurer {
    private final Logger logger = LoggerFactory.getLogger(AsyncEventConfig.class);

    @Value("${web.poolSize}")
    private Integer poolSize;
    @Value("${web.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${web.allowCoreThreadTimeOut}")
    private boolean allowCoreThreadTimeOut;
    @Value("${web.queueCapacity}")
    private Integer queueCapacity;
    @Value("${web.keepAliveSeconds}")
    private Integer keepAliveSeconds;
    @Value("${web.threadNamePrefix}")
    private String threadNamePrefix;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executorService = new ThreadPoolTaskExecutor();
        executorService.setCorePoolSize(poolSize);
        executorService.setMaxPoolSize(maxPoolSize);
        executorService.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
        executorService.setQueueCapacity(queueCapacity);
        executorService.setKeepAliveSeconds(keepAliveSeconds);
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.setThreadNamePrefix(threadNamePrefix);
        executorService.initialize();
        if (logger.isDebugEnabled()) {
            logger.info("使用自定义线程池，{}", JSON.toJSONString(executorService));
        }
        return executorService;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            logger.info("异常描述：{}，方法：{}，参数：{}", throwable.getMessage(), method.getName(), Arrays.toString(objects));
        };
    }
}
