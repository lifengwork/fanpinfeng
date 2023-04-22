package com.maibaduoduo.base.other.queue.lock;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class Synchronized {
    public void syncharonized() throws InterruptedException {
       try{
           synchronized (this){
               Thread.sleep(10000);
               System.out.println("测试 syncharonized "+ Thread.currentThread().getId());
           }
       }catch(InterruptedException e){
           System.out.println("测试 中断 "+ Thread.currentThread().getId());
       }
    }

    public static void main(String[] args) throws InterruptedException {
        Synchronized s = new Synchronized();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s.syncharonized();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s.syncharonized();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t2.start();
        Thread.sleep(5000);
        t2.interrupt();
    }
}
