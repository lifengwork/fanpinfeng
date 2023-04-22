package com.maibaduoduo.base.other.queue.exception;

import org.apache.avalon.framework.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public class ExceptionHandlerThreadPool extends ThreadPoolExecutor {
    private static Logger log = LoggerFactory.getLogger(ExceptionHandlerThreadPool.class);
    public ExceptionHandlerThreadPool(int corePoolSize,
                                      int maximumPoolSize,
                                      long keepAliveTime,
                                      TimeUnit unit,
                                      BlockingQueue<Runnable> workQueue,
                                      RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        this.setRejectedExecutionHandler((r, executor) -> System.out.println("系统繁忙,请求失败,请稍后再试。"));//连接拒绝中断异常
    }

    /**
     * 这里的Throwable拦截的时线程池中线程抛出的异常。
     * @param r
     * @param t
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t == null && r instanceof Future<?>) {
            try {
                Object result = ((Future<?>) r).get();
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // ignore/reset
            }
        }
        if (t != null)
            log.info(ExceptionUtil.printStackTrace(t));//打印异常堆栈信息
    }
}

