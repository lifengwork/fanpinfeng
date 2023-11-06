/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.task.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import com.maibaduoduo.logistics.deliveryman.task.factory.ProgramEventFactory;
import com.maibaduoduo.logistics.deliveryman.task.factory.ProgramThreadFactory;
import com.maibaduoduo.logistics.deliveryman.task.program.Program;
import org.springframework.beans.factory.DisposableBean;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ProgramConfig implements DisposableBean {
    protected static final int MAX_THREAD = Runtime.getRuntime().availableProcessors();// << 1;

    protected Disruptor<ProgramEvent> disruptor;

    protected final Program program;

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
        }, ProducerType.SINGLE, new BlockingWaitStrategy());

        final Executor executor = new ThreadPoolExecutor(MAX_THREAD, MAX_THREAD, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                ProgramThreadFactory.create("retalier-log-disruptor", false),
                new ThreadPoolExecutor.AbortPolicy());

        this.configHandler(executor);

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
    protected abstract Disruptor<ProgramEvent> configHandler(final Executor executor );
}
