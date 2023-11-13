package com.maibaduoduo.store.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.store.task.aspect.Repetition;
import com.maibaduoduo.store.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

/**
 * 计算运费
 */
@Component
public class FreightEventHandler implements EventHandler<ProgramEvent> {

    @Override
    @Repetition
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         *2计算运费
         *
         */

    }
}