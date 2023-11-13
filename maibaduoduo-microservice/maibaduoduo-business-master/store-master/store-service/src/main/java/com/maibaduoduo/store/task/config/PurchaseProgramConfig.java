/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.maibaduoduo.store.task.event.PurchaseProgramEvent;
import com.maibaduoduo.store.task.factory.AutoExecutor;
import com.maibaduoduo.store.task.factory.ProgramThreadFactory;
import com.maibaduoduo.store.task.factory.PurchaseProgramEventFactory;
import com.maibaduoduo.store.task.factory.PurchaseProgramThreadFactory;
import com.maibaduoduo.store.task.program.Program;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PurchaseProgramConfig implements DisposableBean {
    protected Log log = LogFactory.getLog(this.getClass());
    protected static final int MAX_THREAD = Runtime.getRuntime().availableProcessors();// << 1;

    protected Disruptor<PurchaseProgramEvent> disruptor;

    protected final Program program;

    public PurchaseProgramConfig(Program program) {
        this.program = program;
    }

    /**
     * start disruptor.
     *
     * @param bufferSize bufferSize
     */
    public void start(final int bufferSize) {
        disruptor = new Disruptor<PurchaseProgramEvent>(new PurchaseProgramEventFactory(), bufferSize, r -> {
            AtomicInteger index = new AtomicInteger(1);
            Thread thread = new Thread(null, r, "purchase-disruptor-thread-" + index.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, e) -> log.error(t + "ERROR: " + e));
            return thread;
    }, ProducerType.MULTI, new BlockingWaitStrategy());

        final Executor executor = new AutoExecutor(
                MAX_THREAD,
                MAX_THREAD,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                PurchaseProgramThreadFactory.create("purchase-log-disruptor", false),
                new ThreadPoolExecutor.AbortPolicy());

        this.configHandler(executor);

        disruptor.setDefaultExceptionHandler(new IgnoreExceptionHandler());
        disruptor.start();

        this.afterProcessor();
    }

    public Disruptor<PurchaseProgramEvent> programConfig() {
        return disruptor;
    }

    @Override
    public void destroy() {
        disruptor.shutdown();
    }
    protected abstract Disruptor<PurchaseProgramEvent> configHandler(final Executor executor );
    protected abstract void afterProcessor();
}
