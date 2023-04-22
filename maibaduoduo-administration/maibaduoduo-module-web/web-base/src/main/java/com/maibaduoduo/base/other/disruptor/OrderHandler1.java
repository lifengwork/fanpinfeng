package com.maibaduoduo.base.other.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by Administrator on 2019/11/1 0001.
 */
//EventHandler用于EventHandlerGroup，WorkHandler用于WorkPool。同时实现两接口，该类对象可同时用于EventHandlerGroup和WorkPool
public class OrderHandler1 implements EventHandler<Order>, WorkHandler<Order> {
    private String consumerId;
    public OrderHandler1(String consumerId){
        this.consumerId = consumerId;
    }

    //EventHandler的方法
    @Override
    public void onEvent(Order order, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("OrderHandler1 " + this.consumerId + "，消费信息：" + order.getId());
    }

    //WorkHandler的方法
    @Override
    public void onEvent(Order order) throws Exception {
        System.out.println("OrderHandler1 " + this.consumerId + "，消费信息：" + order.getId());
    }
}