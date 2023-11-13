package com.maibaduoduo.purchase.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class EventConfigThreadFactory {
    @Bean
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, linkedBlockingQueue);
        simpleApplicationEventMulticaster.setTaskExecutor(threadPoolExecutor);
        return simpleApplicationEventMulticaster;
    }
}
