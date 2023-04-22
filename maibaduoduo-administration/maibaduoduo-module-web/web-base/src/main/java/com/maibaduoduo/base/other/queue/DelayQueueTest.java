package com.maibaduoduo.base.other.queue;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/10/30 0030.
 * 延迟队列，就是设置一定的时间，到时触发运行业务逻辑
 * 使用场景：
 *    1，用在有些需要延迟执行的任务，省区使用定时任务。
 */
public class DelayQueueTest {
    public int flag=0;
    public static void main(String[] args) throws InterruptedException {
        DelayQueueTest delayQueueTest = new DelayQueueTest();

        // 创建延时队列
        DelayQueue<Message> queue = new DelayQueue<Message>();
       /* Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        int flag = sc.nextInt();
        delayQueueTest.producer(queue,msg,flag);*/
        // 添加延时消息,m1 延时3s
        Message m1 = new Message(1, "world", 3000);
        // 添加延时消息,m2 延时10s
        Message m2 = new Message(2, "hello", 10000);
        //将延时消息放到延时队列中
        queue.offer(m2);
        queue.offer(m1);
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        // ExecutorService exec = Executors.newFixedThreadPool(2);//固定数量的线程池默认为1个
        ExecutorService executor = new ThreadPoolExecutor(2, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        executor.execute(new DelayQueueMessageConsumer(queue));
        //executor.shutdown();
    }
    private void producer(DelayQueue delayQueue,String msg,int flag ) throws InterruptedException {
        while(flag==0){
            TimeUnit.SECONDS.sleep(1);
            int int_ = new Random(10).nextInt();
            delayQueue.offer(new Message(int_,msg+int_,3000));
        }
    }
}
