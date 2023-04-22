package com.maibaduoduo.base.other.disruptor.park;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * 负责保存进入停车场的汽车信息
 * Created by Administrator on 2019/11/1 0001.
 */
public class ParkingCarDataInDbHandler implements EventHandler<ParkEvent>,WorkHandler<ParkEvent> {

    @Override
    public void onEvent(ParkEvent parkEvent) throws Exception {
        long threadId = Thread.currentThread().getId(); // 获取当前线程id
        String carLicense = parkEvent.getCarlicense(); // 获取车牌号
        System.out.println(String.format("Thread Id %s 保存 %s 到数据库中 ....", threadId, carLicense));
    }

    @Override
    public void onEvent(ParkEvent parkEvent, long sequence, boolean endOfBatch)
            throws Exception {
        this.onEvent(parkEvent);
    }

}
