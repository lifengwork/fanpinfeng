package com.maibaduoduo.store.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.store.task.aspect.Repetition;
import com.maibaduoduo.store.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

/**
 * 生成采购协议
 */
@Component
public class PurchseContractEventHandler implements EventHandler<ProgramEvent> {
    @Override
    @Repetition
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         * 生成采购协议
         *
         */
    }
}