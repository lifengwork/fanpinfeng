package com.maibaduoduo.task.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class ProgramThreadFactory  implements ThreadFactory {
    private boolean daemon;
    private final ThreadGroup THREAD_GROUP = new ThreadGroup("Program");
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
        Thread thread = new Thread(this.THREAD_GROUP, runnable, this.THREAD_GROUP.getName() + "-" + this.namePrefix + "-" + this.threadNumber.getAndIncrement());
        thread.setDaemon(this.daemon);
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }

        return thread;
    }
}
