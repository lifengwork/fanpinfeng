package com.maibaduoduo.store.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.store.task.aspect.Repetition;
import com.maibaduoduo.store.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

/**
 * 选择运输方式
 */
@Component
public class VehicleEventHandler implements EventHandler<ProgramEvent> {
    @Override
    @Repetition
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         *2选择运输方式
         *
         */
    }
}