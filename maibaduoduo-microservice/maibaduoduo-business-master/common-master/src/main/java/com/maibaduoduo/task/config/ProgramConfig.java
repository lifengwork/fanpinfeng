/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.config;

import cn.hutool.core.date.DateUtil;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.factory.AutoExecutor;
import com.maibaduoduo.task.factory.ProgramEventFactory;
import com.maibaduoduo.task.factory.ProgramThreadFactory;
import com.maibaduoduo.task.program.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ProgramConfig implements DisposableBean {
    protected Logger logger = LoggerFactory.getLogger(ProgramConfig.class);
    protected static final int MAX_THREAD = Runtime.getRuntime().availableProcessors();// << 1;

    protected Disruptor<ProgramEvent> disruptor;

    protected final Program program;
    protected String businessType;

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
            Thread thread = new Thread(null, r, businessType + "-disruptor-thread-" + index.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, e) -> logger.error(t + "ERROR: " + e));
            return thread;
        }, ProducerType.MULTI, new BlockingWaitStrategy());

        final Executor executor = new AutoExecutor(
                MAX_THREAD,
                MAX_THREAD,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                ProgramThreadFactory.create(businessType + "-log-disruptor", false),
                new ThreadPoolExecutor.AbortPolicy());

        this.configHandler(executor);

        disruptor.setDefaultExceptionHandler(new IgnoreExceptionHandler());
        disruptor.start();
        logger.info(businessType + "success start " + DateUtil.now());

        this.afterProcessor();
    }

    public Disruptor<ProgramEvent> programConfig() {
        return disruptor;
    }

    @Override
    public void destroy() {
        disruptor.shutdown();
    }

    protected abstract Disruptor<ProgramEvent> configHandler(final Executor executor);

    protected abstract void afterProcessor();

    protected abstract void initType(String businesstype);
}
