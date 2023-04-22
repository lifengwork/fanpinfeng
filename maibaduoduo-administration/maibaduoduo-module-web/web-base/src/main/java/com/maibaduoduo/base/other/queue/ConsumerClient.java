package com.maibaduoduo.base.other.queue;


import com.maibaduoduo.base.other.queue.exception.ExceptionHandlerThreadPool;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class ConsumerClient {
    //创建延时队列
    private static LinkedBlockingDeque<Msg> queue = null;
    /**
     *  类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了惰性加载
     */
    private static class SingletonHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private  static LinkedBlockingDeque<Msg> queue = new LinkedBlockingDeque<>();
    }

    /**
     *
     * @return
     */
    public static LinkedBlockingDeque getMailQueue(){
        return SingletonHolder.queue;
    }
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque queue = ConsumerClient.getMailQueue();
        ThreadFactory threadFactory = r -> {
            final Thread thread = new Thread();
            thread.setUncaughtExceptionHandler((t, e) -> System.out.println("自定义异常处理器：系统繁忙,请求失败,请稍后再试。"));
            return thread;
        };
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExceptionHandlerThreadPool producerPool = new ExceptionHandlerThreadPool(5,10,10, TimeUnit.SECONDS,new LinkedBlockingDeque<>(),new ThreadPoolExecutor.AbortPolicy());//固定数量的线程池默认为1个
        Thread t1 = new Thread(() -> {
            int i = 300;
            while(i>0){
                try {
                    producerPool.execute(new Producer(queue,i));//模拟一个用户发起的请求
                    i--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
           }
            producerPool.shutdown();
        });

        ExecutorService consumerPool = Executors.newFixedThreadPool(200,threadFactory);//固定数量的线程池默认为1个
        Thread t2 = new Thread(() -> {
            while(queue.size()>0){
                try {
                    consumerPool.execute(new Consumer(queue));//模拟一个用户发起的消费请求
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            consumerPool.shutdown();
        });
        Thread t3 = new Thread(() -> {
            while(queue.size()>0){
                try {
                    Thread.sleep(1000);
                    System.out.println("===========================================待消费的消息个数：" + queue.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread.sleep(10000);
        t3.start();
        t2.start();
    }
}