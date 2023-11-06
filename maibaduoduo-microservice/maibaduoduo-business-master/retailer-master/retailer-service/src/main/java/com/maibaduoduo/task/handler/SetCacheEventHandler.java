package com.maibaduoduo.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

@Component
public class SetCacheEventHandler implements EventHandler<ProgramEvent> {
    @Override
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO 放入缓存推送到区域展示
         */
    }
}