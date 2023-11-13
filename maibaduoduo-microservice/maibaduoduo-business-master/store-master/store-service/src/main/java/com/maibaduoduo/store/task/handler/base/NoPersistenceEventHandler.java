package com.maibaduoduo.store.task.handler.base;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.store.task.config.EventContants;
import com.maibaduoduo.store.task.event.ProgramEvent;
import com.maibaduoduo.store.task.event.PurchaseProgramEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class NoPersistenceEventHandler implements EventHandler<ProgramEvent> {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        redisTemplate.boundHashOps(EventContants.E_RAWKEY).delete(programEvent.getProgramTask()
                .getExecuteObject().getExecuteId());
    }
}
