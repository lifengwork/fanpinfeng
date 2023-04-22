package com.maibaduoduo.base.other.queue;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class T1 extends Thread {
    @Override
    public void run() {
        super.run();
        Integer i=0;
        Integer index = i++;
        System.out.println(index);
    }
}
