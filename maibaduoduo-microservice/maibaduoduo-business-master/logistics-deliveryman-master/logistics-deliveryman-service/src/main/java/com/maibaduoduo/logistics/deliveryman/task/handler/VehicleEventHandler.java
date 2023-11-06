package com.maibaduoduo.logistics.deliveryman.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

@Component
public class VehicleEventHandler implements EventHandler<ProgramEvent> {
    @Override
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         * 运输方式选择，检查迅速网络和路径以及运输方式状态（车辆状态）
         *
         */
    }
}