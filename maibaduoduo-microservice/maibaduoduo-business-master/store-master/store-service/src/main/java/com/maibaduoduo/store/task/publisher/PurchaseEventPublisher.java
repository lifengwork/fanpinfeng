/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.publisher;

import com.lmax.disruptor.RingBuffer;
import com.maibaduoduo.task.config.EventContants;
import com.maibaduoduo.task.config.ProgramConfig;
import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.event.ProgramTask;
import com.maibaduoduo.task.translator.ProgramEventTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * ProgramEventPublisher.
 *
 * @author saas
 */
@Component
public class PurchaseEventPublisher {

    @Autowired
    @Qualifier("purchaseConfigHandler")
    private ProgramConfig purchaseProgramConfig;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * publish program event.
     * @param programTask
     * @param type
     */
    public void publishEvent(final ProgramTask programTask, final int type) {
        final RingBuffer<ProgramEvent> ringBuffer = purchaseProgramConfig.programConfig().getRingBuffer();
        ringBuffer.publishEvent(new ProgramEventTranslator(type), programTask);
        redisTemplate.boundHashOps(EventContants.E_RAWKEY).put(
                String.valueOf(programTask.getExecuteObject().getExecuteId()),
                programTask.getExecuteObject().getEventData().getContent());
    }
}
