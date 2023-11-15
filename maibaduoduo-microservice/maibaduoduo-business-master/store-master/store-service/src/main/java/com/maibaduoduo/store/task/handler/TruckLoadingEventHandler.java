package com.maibaduoduo.store.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.task.aspect.Repetition;
import com.maibaduoduo.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

/**
 * 装车并修改配送单以及订单状态,生成运输单
 */
@Component
public class TruckLoadingEventHandler implements EventHandler<ProgramEvent> {
    @Override
    @Repetition
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         *装车并修改配送单以及订单状态,生成运输单
         *
         */
    }
}