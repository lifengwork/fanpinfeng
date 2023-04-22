package com.maibaduoduo.base.other.queue.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class SemaphoreCommunication {
    public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(2);
    new SendingThread(semaphore,"SendingThread");
    new ReceivingThread(semaphore,"ReceivingThread");
}
}

class SendingThread extends Thread {
    Semaphore semaphore;
    String name;

    public SendingThread(Semaphore semaphore,String name) {
        this.semaphore = semaphore;
        this.name = name;
        new Thread(this).start();
    }

    public void run() {
        try {
            semaphore.acquire();
            for (int i = 0; i < 5; i++) {
                System.out.println(name + ":" + i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        semaphore.release();
    }
}

class ReceivingThread extends Thread {
    Semaphore semaphore;
    String name;

    public ReceivingThread(Semaphore semaphore,String name) {
        this.semaphore = semaphore;
        this.name = name;
        new Thread(this).start();
    }

    public void run() {
        try {
            semaphore.acquire();
            for (int i = 0; i < 5; i++) {
                System.out.println(name + ":" + i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        semaphore.release();
    }
}