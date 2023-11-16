package com.maibaduoduo.store.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.task.aspect.Repetition;
import com.maibaduoduo.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

/**
 * 选择最优路线
 */
@Component
public class InventoryEventHandler implements EventHandler<ProgramEvent> {
    @Override
    @Repetition
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         *3选择最优路线
         *
         */
    }
}