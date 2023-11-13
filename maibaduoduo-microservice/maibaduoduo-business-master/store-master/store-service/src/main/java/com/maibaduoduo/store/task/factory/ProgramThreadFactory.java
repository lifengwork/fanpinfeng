/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.factory;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class ProgramThreadFactory implements ThreadFactory {
    private Log log = LogFactory.getLog(this.getClass());
    private boolean daemon;
    private final ThreadGroup THREAD_GROUP = new ThreadGroup("Store-Program");
    private final AtomicLong threadNumber = new AtomicLong(1L);
    private final String namePrefix;

    private ProgramThreadFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
    }

    public static ThreadFactory create(String namePrefix, boolean daemon) {
        return new ProgramThreadFactory(namePrefix, daemon);
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(
                this.THREAD_GROUP,
                runnable,
                this.THREAD_GROUP.getName() + "-" + this.namePrefix + "-" + this.threadNumber.getAndIncrement());
        thread.setDaemon(this.daemon);
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.error(t + "ERROR:" + e);
            }
        });
        return thread;
    }
}
