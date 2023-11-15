package com.maibaduoduo.store.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.task.aspect.Repetition;
import com.maibaduoduo.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

/**
 * 拣货,打包出库
 */
@Component
public class PickingEventHandler implements EventHandler<ProgramEvent> {
    @Override
    @Repetition
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         *拣货,打包出库
         *生成箱啤数据
         */
    }
}