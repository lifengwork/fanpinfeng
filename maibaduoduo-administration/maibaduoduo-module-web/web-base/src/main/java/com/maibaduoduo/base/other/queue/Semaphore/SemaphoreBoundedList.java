package com.maibaduoduo.base.other.queue.Semaphore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class SemaphoreBoundedList {
    public static void main(String[] args) {
        //4、容器边界限制
        final BoundedList ba = new BoundedList(5);
        Runnable runnable1 = new Runnable() {
            public void run() {
                try {
                    ba.add("John");
                    ba.add("Martin");
                    ba.add("Adam");
                    ba.add("Prince");
                    ba.add("Tod");
                    System.out.println("Available Permits : " + ba.getSemaphore().availablePermits());
                    ba.add("Tony");//额外增加的资源
                    ba.add("Tony1");//额外增加的资源
                    ba.add("TonyTest");//额外增加的资源
                    System.out.println("Final list: " + ba.getArrayList());////5-2+3+1
                }catch (InterruptedException ie) {
                    Thread.interrupted();
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            public void run() {
                try {
                    System.out.println("Before removing elements: "+ ba.getArrayList());
                    Thread.sleep(5000);
                    ba.remove("Martin");
                    ba.remove("Adam");
                    if(!ba.getSemaphore().tryAcquire(2, TimeUnit.SECONDS)){
                        System.out.println("outofrange " + Thread.currentThread().getState());
                        ba.getSemaphore().release(1);//增大许可数量并释放资源，前提是要需要的额外的许可相等否则阻塞
                    }
                }catch (InterruptedException ie) {
                    //Thread.interrupted();
                    System.out.println("outofrange " + Thread.currentThread().getState());
                }
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }
}

class BoundedList<T> {
    private final Semaphore semaphore;
    private List arrayList;

    BoundedList(int limit) {
        this.arrayList = Collections.synchronizedList(new ArrayList());
        this.semaphore = new Semaphore(limit);
    }

    public boolean add(T t) throws InterruptedException {
        boolean added = false;
        semaphore.acquire();
        try {
            added = arrayList.add(t);
            return added;
        } finally {
            if (!added)
                semaphore.release();
        }
    }

    public boolean remove(T t) {
        boolean wasRemoved = arrayList.remove(t);
        if (wasRemoved)
            semaphore.release();
        return wasRemoved;
    }

    public void remove(int index) {
        arrayList.remove(index);
        semaphore.release();
    }

    public List getArrayList() {
        return arrayList;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}