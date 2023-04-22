package com.maibaduoduo.base.other.queue;

import java.util.concurrent.DelayQueue;

public class DelayQueueMessageConsumer extends Thread {
    public DelayQueue<Message> delayQueue = null;

    public DelayQueueMessageConsumer(DelayQueue delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        while (delayQueue.size() > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message = delayQueue.poll();
            if (null != message) {
                System.out.println("消费者，" + message.getExcuteTime());
                System.out.println("消费者，" + message.getBody());
            }

        }
        throw new RuntimeException();
    }

    @Override
    public void setUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
        System.out.println("抛出异常，" + eh);
    }
}
