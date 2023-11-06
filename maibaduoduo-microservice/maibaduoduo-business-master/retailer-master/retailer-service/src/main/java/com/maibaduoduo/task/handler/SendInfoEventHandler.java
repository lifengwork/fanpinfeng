package com.maibaduoduo.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

@Component
public class SendInfoEventHandler implements EventHandler<ProgramEvent> {
    @Override
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         * 发消息通知 下单用户所在区域配送员
         *
         */
    }
}