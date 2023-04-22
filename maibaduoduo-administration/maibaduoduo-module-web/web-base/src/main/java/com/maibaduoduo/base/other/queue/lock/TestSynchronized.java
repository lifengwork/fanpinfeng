package com.maibaduoduo.base.other.queue.lock;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class TestSynchronized {
    public synchronized void f1() {
        System.out.println("synchronized void f1()");
    }

    public void f2() {
        synchronized(this) {
            System.out.println("synchronized(this)");
        }
    }

    public static void main(String[] args) {
        TestSynchronized test = new TestSynchronized();
        test.f1();
        test.f2();
    }
}
