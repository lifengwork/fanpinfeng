package com.maibaduoduo.logistics.deliveryman.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.logistics.deliveryman.task.aspect.Repetition;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

@Component
public class SendInfoEventHandler implements EventHandler<ProgramEvent> {
    @Override
    @Repetition
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         * 发消息通知配送员准备配送
         *
         */
    }
}