package com.maibaduoduo.logistics.deliveryman.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

@Component
public class CreateDeliverEventHandler implements EventHandler<ProgramEvent> {
    @Override
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         * 创建配送清单
         *
         */
    }
}