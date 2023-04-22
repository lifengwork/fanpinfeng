package com.maibaduoduo.base.other.queue;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class Producer extends Thread {
    //创建延时队列
    private  LinkedBlockingDeque<Msg> queue = null;
    private int index;
    public Producer(LinkedBlockingDeque<Msg> queue, int index) {
        this.queue  = queue;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            int Max = 100,Min=1;
            int random = (int)(Math.random()*(Max-Min)+Min);
            Msg message = new Msg(index, random+"");
            queue.offer(message);
            System.out.println("********************************加入队列成功："+queue.getLast());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
