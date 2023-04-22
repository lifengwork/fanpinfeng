package com.maibaduoduo.base.other.queue.ExecutorDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池实例化说明：
 * public ThreadPoolExecutor(int corePoolSize,
     int maximumPoolSize,最大线程容量
     long keepAliveTime,闲置线程失效时间
     TimeUnit unit,时间单位：如秒，毫秒等。
     BlockingQueue<Runnable> workQueue) {任务排队队列
     this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,Executors.defaultThreadFactory(), defaultHandler);
     }
     defaultHandler:默认的任务拒绝策略
 *
 * Created by Administrator on 2019/10/31 0031.
 */
public class ExecutorPolicy {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),new ThreadPoolExecutor.AbortPolicy());
        //捕获处理失败的异常
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("系统繁忙,请求失败,请稍后再试。");
                /**
                 * To Do 加入其他业务处理代码
                 */
            }
        });
        for (int i = 0; i < 20; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}

class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("线程名称：" + Thread.currentThread().getName() + "，正在执行task " + taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + "执行完毕");
    }
}
