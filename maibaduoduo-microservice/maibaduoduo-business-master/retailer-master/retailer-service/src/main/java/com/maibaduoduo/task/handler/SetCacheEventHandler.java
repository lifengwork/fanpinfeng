package com.maibaduoduo.task.handler;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.order.entity.SaasOrderEntity;
import com.maibaduoduo.task.event.ProgramEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SetCacheEventHandler implements EventHandler<ProgramEvent> {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         * 放入缓存推送到区域展示，根据下单用户区域代码放入缓存，
         * 缓存key通过区域代码区分
         */
        String eventContent = programEvent.getProgramTask().getExecuteObject().getEventData().getContent();
        SaasOrderEntity saasOrderEntity  = JSON.parseObject(eventContent,SaasOrderEntity.class);
        redisTemplate.
                boundHashOps(String.valueOf(programEvent.getProgramTask().getExecuteObject().getEventData().getAreaCode())).
                put(saasOrderEntity.getOrderNo(),eventContent);
    }
}