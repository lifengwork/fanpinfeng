package com.maibaduoduo.logistics.deliveryman.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.logistics.deliveryman.task.aspect.Repetition;
import com.maibaduoduo.logistics.deliveryman.task.config.EventContants;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreateDeliverEventHandler implements EventHandler<ProgramEvent> {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Repetition
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         * 创建配送清单
         *
         */
    }
}