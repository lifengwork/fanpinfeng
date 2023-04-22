package com.maibaduoduo.base.other.queue.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class SemaphoreLock {
    public static void main(String[] args) {
        //1、信号量为1时 相当于普通的锁  信号量大于1时 共享锁
        Output o = new Output();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> o.output()).start();
        }
    }
}

class Output {
    Semaphore semaphore = new Semaphore(1);

    public void output() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " start at " + System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " stop at " + System.currentTimeMillis());
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
