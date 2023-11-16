package com.maibaduoduo.task.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 自定义线程池
 */
public class AutoExecutor extends ThreadPoolExecutor {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    public AutoExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public AutoExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public AutoExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public AutoExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        Runtime runtime = Runtime.getRuntime();
        log.info("当前线程:{0}," +
                        "CPU数量:{1}," +
                        "空闲内存:{2}, " +
                        "最大内存:{}," +
                        "总内存:{3}",
                t.getName(),
                runtime.availableProcessors(),
                runtime.freeMemory(),
                runtime.maxMemory(),
                runtime.totalMemory()
        );
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        log.info("Threadname : {0}," +
                        "| currentthreadstatus :{1}," +
                "| Currentthreadid : {2},|poolSize :{3},| taskCount:{4},| completedTaskCount：{5},nocompletedTaskCount：{6}",
                r.getClass().getSimpleName(),Thread.currentThread().getState(),
                Thread.currentThread().getId(),this.getPoolSize(),this.getTaskCount(),this.getCompletedTaskCount(),(this.getTaskCount()-this.getCompletedTaskCount()));
    }
}
