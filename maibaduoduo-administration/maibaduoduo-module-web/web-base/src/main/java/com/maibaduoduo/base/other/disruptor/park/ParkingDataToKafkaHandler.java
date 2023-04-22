package com.maibaduoduo.base.other.disruptor.park;

import com.lmax.disruptor.EventHandler;

/**
 * 责发送通知告知工作人员(Kafka是一种高吞吐量的分布式发布订阅消息系统)
 * Created by Administrator on 2019/11/1 0001.
 */
public class ParkingDataToKafkaHandler implements EventHandler<ParkEvent> {

    @Override
    public void onEvent(ParkEvent parkEvent, long l, boolean b) throws Exception {
        long threadId = Thread.currentThread().getId(); // 获取当前线程id
        String carLicense = parkEvent.getCarlicense(); // 获取车牌号
        System.out.println(String.format("Thread Id %s 发送 %s 进入停车场信息给 kafka系统...", threadId, carLicense));
    }

}
