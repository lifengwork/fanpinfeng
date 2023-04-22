package com.maibaduoduo.base.other.queue;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class Consumer extends Thread {
    // 延时队列 ,消费者从其中获取消息进行消费
    private LinkedBlockingDeque<Msg> queue;
    public Consumer(LinkedBlockingDeque<Msg> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //while (queue.size()>0) {
        Msg take = queue.poll();
        if(null!=take)
        System.out.println("消费消息id：" + take.getId() + " 消息体：" + take.getBody());

        // }
    }
    @Override
    public void setUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
        super.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("系统繁忙,请求失败,请稍后再试。");
            }
        });
    }
}