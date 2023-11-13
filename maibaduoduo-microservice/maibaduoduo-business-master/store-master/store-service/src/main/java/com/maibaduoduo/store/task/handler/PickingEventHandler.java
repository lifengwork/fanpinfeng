package com.maibaduoduo.store.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.store.task.aspect.Repetition;
import com.maibaduoduo.store.task.event.ProgramEvent;
import com.maibaduoduo.store.task.event.PurchaseProgramEvent;
import org.springframework.stereotype.Component;

/**
 * 拣货,打包出库
 */
@Component
public class PickingEventHandler implements EventHandler<PurchaseProgramEvent> {
    @Override
    @Repetition
    public void onEvent(PurchaseProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         *拣货,打包出库
         *
         */
    }
}