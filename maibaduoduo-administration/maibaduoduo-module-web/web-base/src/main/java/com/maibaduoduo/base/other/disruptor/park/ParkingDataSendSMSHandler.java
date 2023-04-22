package com.maibaduoduo.base.other.disruptor.park;

import com.lmax.disruptor.EventHandler;

/**
 * sms短信服务，告知司机你已经进入停车场，计费开始
 * Created by Administrator on 2019/11/1 0001.
 */
public class ParkingDataSendSMSHandler implements EventHandler<ParkEvent> {
    @Override
    public void onEvent(ParkEvent parkEvent, long sequence, boolean endOfBatch)
            throws Exception {
        long threadId = Thread.currentThread().getId(); // 获取当前线程id
        String carLicense = parkEvent.getCarlicense(); // 获取车牌号
        System.out.println(String.format("Thread Id %s 给  %s 的车主发送一条短信，并告知他计费开始了 ....", threadId, carLicense));
    }
}
