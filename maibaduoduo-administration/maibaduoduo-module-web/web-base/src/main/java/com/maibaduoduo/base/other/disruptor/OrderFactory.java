package com.maibaduoduo.base.other.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by Administrator on 2019/11/1 0001.
 */
public class OrderFactory implements EventFactory<Order> {
    @Override
    public Order newInstance() {
        return new Order();
    }
}