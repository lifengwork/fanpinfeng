package com.maibaduoduo.base.other.queue.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class ReentrantLockDemo  {
    private Lock lock = new ReentrantLock();//当前对象可重入
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();//当前对象可重入
    private List list = new ArrayList<String>();
    Condition condition = lock.newCondition();
    /**
     * 入队列的阻塞 读锁
     * @param string
     */
    public void offerReentrantLock(String string) throws InterruptedException {
        try {
            lock.lock();
            System.out.println("当前线程Start：" + Thread.currentThread().getId() + " 编号：" + list.size());
            list.add(string);
            //condition.await();
            System.out.println("当前线程End：" + Thread.currentThread().getId() + " 编号：" + list.size());
        }finally {
            lock.unlock();
        }
    }
    public void signal(){
        lock.lock();
        condition.signal();
        lock.unlock();
    }

    /**
     * 入队列的阻塞 读锁
     * @param string
     */
    public void offer(String string) throws InterruptedException {
        try {
            readWriteLock.writeLock().lock();
            System.out.println("当前线程Start：" + Thread.currentThread().getId() + " 编号：" + list.size());
            list.add(string);
            Thread.sleep(1000);
            System.out.println("当前线程End：" + Thread.currentThread().getId() + " 编号：" + list.size());
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void poll(String string){
        try{
            readWriteLock.readLock().lock();
            System.out.println("当前线程Start："+Thread.currentThread().getId()+" 编号："+list.size());
            if(!list.isEmpty()){
                list.remove(string);
            }
            System.out.println("当前线程End："+Thread.currentThread().getId()+" 编号："+list.size());
        }finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();

        ExecutorService service = Executors.newFixedThreadPool(10);
        int inIndex = 10;
        while(inIndex>0){
           service.execute(new Runnable() {
               @Override
               public void run() {
                   int Max=100,Min=1;
                   try {
                       reentrantLockDemo.offerReentrantLock(String.valueOf((int)(Math.random()*(Max-Min)+Min)));
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           });
            inIndex--;
        }

        Thread.sleep(3000);
        ExecutorService service1 = Executors.newFixedThreadPool(10);
        int inIndex1 = 10;
        while(inIndex1>0){
            Thread.sleep(1000);
            service1.execute(new Runnable() {
                @Override
                public void run() {
                        reentrantLockDemo.signal();
                }
            });
            inIndex1--;
        }
        Thread.sleep(5000);
        service.shutdown();
        service1.shutdown();

    }
}
