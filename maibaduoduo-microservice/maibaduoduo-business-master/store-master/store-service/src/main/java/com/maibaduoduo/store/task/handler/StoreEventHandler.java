package com.maibaduoduo.store.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.store.task.aspect.Repetition;
import com.maibaduoduo.store.task.event.ProgramEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 选择就近仓库
 */
@Component
public class StoreEventHandler implements EventHandler<ProgramEvent> {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Repetition
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         * 1选择就近仓库
         *
         */
    }
}