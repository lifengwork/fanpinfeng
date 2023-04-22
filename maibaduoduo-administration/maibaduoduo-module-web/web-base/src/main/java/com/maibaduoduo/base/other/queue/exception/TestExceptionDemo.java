package com.maibaduoduo.base.other.queue.exception;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public class TestExceptionDemo {
    public static void main(String[] args) {
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExceptionHandlerThreadPool producerPool = new ExceptionHandlerThreadPool(1,1,1, TimeUnit.SECONDS,new LinkedBlockingDeque<>(2),new ThreadPoolExecutor.AbortPolicy());//
       /* producerPool.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("系统繁忙,请求失败,请稍后再试。");
            }
        });*/
        for(int i=1;i<10;i++){
            producerPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("test");
                }
            });
        }
        producerPool.shutdown();
    }
}
