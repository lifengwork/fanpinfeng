/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 * <p>
 * SAAS系统设计研发交流
 * <p>
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.config;

import com.google.common.collect.Lists;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.maibaduoduo.configuration.SaasSpringContextUtil;
import com.maibaduoduo.program.service.ProgramService;
import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.factory.ProgramEventFactory;
import com.maibaduoduo.task.factory.ProgramThreadFactory;
import com.maibaduoduo.task.handler.EventHandler;
import com.maibaduoduo.task.handler.ProgramEventHandler;
import com.maibaduoduo.task.program.Program;
import com.maibaduoduo.utils.BaseContextHandler;
import org.springframework.beans.factory.DisposableBean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgramConfig implements DisposableBean {
    private static final int MAX_THREAD = Runtime.getRuntime().availableProcessors() << 1;

    private Disruptor<ProgramEvent> disruptor;

    private final Program program;

    public ProgramConfig(Program program) {
        this.program = program;
    }

    /**
     * start disruptor.
     *
     * @param bufferSize bufferSize
     */
    public void start(final int bufferSize) {
        disruptor = new Disruptor<>(new ProgramEventFactory(), bufferSize, r -> {
            AtomicInteger index = new AtomicInteger(1);
            return new Thread(null, r, "disruptor-thread-" + index.getAndIncrement());
        }, ProducerType.MULTI, new BlockingWaitStrategy());

        final Executor executor = new ThreadPoolExecutor(MAX_THREAD, MAX_THREAD, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                ProgramThreadFactory.create("program-log-disruptor", false),
                new ThreadPoolExecutor.AbortPolicy());
        List<EventHandler> eventHandlers = null;
        Map<String, EventHandler> eventHandlerMap = SaasSpringContextUtil.getBeansOfType(EventHandler.class);
        for (EventHandler eventHandler : eventHandlerMap.values()) {
            eventHandlers = Lists.newArrayList();
            for (int i = 0; i < MAX_THREAD / 2; i++) {
                eventHandlers.add(eventHandler.programEventHandlerInit(program, executor));
            }
            //
            EventHandler[] consumers = new EventHandler[eventHandlers.size()];
            for (int i = 0; i < eventHandlers.size(); i++) {
                consumers[i] = eventHandlers.get(i);
            }
            disruptor.handleEventsWithWorkerPool(consumers).asSequenceBarrier();
        }

        disruptor.setDefaultExceptionHandler(new IgnoreExceptionHandler());
        disruptor.start();
    }

    public Disruptor<ProgramEvent> programConfig() {
        return disruptor;
    }

    @Override
    public void destroy() {
        disruptor.shutdown();
    }
}
