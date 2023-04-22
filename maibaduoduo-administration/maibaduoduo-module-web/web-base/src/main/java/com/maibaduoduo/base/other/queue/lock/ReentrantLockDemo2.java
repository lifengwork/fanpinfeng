package com.maibaduoduo.base.other.queue.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class ReentrantLockDemo2 {
    private Lock lock = new ReentrantLock();//当前对象可重入
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();//当前对象可重入
    private List list = new ArrayList<String>();
    Condition condition = lock.newCondition();
    /**
     * 入队列的阻塞 读锁
     * @param string
     */
    public void offerReentrantLock(String string) throws InterruptedException {
        System.out.println("***********当前线程Start：" + Thread.currentThread().getId());
        try {
            lock.lock();
            System.out.println("当前线程Start：" + Thread.currentThread().getId() + " 编号：" + list.size());
            list.add(string);
            //condition.await();
            //TimeUnit.SECONDS.sleep(100);
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
    public void lockInterruptibly() throws InterruptedException {
      try{
              lock.lockInterruptibly();
              TimeUnit.SECONDS.sleep(10);
              System.out.println("***********当前线程Start：" + Thread.currentThread().getId());
              lock.lockInterruptibly();
      }catch (InterruptedException e){
          System.out.println("***********当前线程lockInterruptibly " + Thread.currentThread().getId()+" 被中断。");
      }finally {
          lock.unlock();
          System.out.println("***********当前线程End：" + Thread.currentThread().getId());
      }
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
        ReentrantLockDemo2 reentrantLockDemo = new ReentrantLockDemo2();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int Max = 100, Min = 1;
                try {
                    reentrantLockDemo.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int Max = 100, Min = 1;
                try {
                    reentrantLockDemo.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t2.interrupt();
    }
}
