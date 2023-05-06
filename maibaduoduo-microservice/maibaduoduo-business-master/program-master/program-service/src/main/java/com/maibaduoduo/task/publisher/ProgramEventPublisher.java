/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.publisher;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.maibaduoduo.program.service.ProgramService;
import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.event.ProgramTask;
import com.maibaduoduo.task.factory.ProgramEventFactory;
import com.maibaduoduo.task.factory.ProgramThreadFactory;
import com.maibaduoduo.task.handler.ProgramEventHandler;
import com.maibaduoduo.task.translator.ProgramEventTranslator;
import org.dromara.myth.common.bean.entity.MythTransaction;
import org.dromara.myth.common.enums.EventTypeEnum;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ProgramEventPublisher.
 *
 * @author saas
 */
@Component
public class ProgramEventPublisher implements DisposableBean {

    private static final int MAX_THREAD = Runtime.getRuntime().availableProcessors() << 1;

    private Disruptor<ProgramEvent> disruptor;

    private final ProgramService programService;

    @Autowired
    public ProgramEventPublisher(ProgramService programService) {
        this.programService = programService;
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

        ProgramEventHandler[] consumers = new ProgramEventHandler[MAX_THREAD];
        for (int i = 0; i < MAX_THREAD; i++) {
            consumers[i] = new ProgramEventHandler(programService, executor);
        }
        disruptor.handleEventsWithWorkerPool(consumers);
        disruptor.setDefaultExceptionHandler(new IgnoreExceptionHandler());
        disruptor.start();
    }


    /**
     * publish disruptor event.
     *
     * @param programTask {@linkplain MythTransaction }
     * @param type            {@linkplain EventTypeEnum}
     */
    public void publishEvent(final ProgramTask programTask, final int type) {
        final RingBuffer<ProgramEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(new ProgramEventTranslator(type), programTask);
    }

    @Override
    public void destroy() {
        disruptor.shutdown();
    }
}
