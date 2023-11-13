package com.maibaduoduo.store.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.store.task.aspect.Repetition;
import com.maibaduoduo.store.task.event.ProgramEvent;
import com.maibaduoduo.store.task.event.PurchaseProgramEvent;
import org.springframework.stereotype.Component;

/**
 * 选择最优路线
 */
@Component
public class InventoryEventHandler implements EventHandler<PurchaseProgramEvent> {
    @Override
    @Repetition
    public void onEvent(PurchaseProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         *3选择最优路线
         *
         */
    }
}