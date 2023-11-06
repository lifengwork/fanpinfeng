/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.task.publisher;

import com.lmax.disruptor.RingBuffer;
import com.maibaduoduo.logistics.deliveryman.task.config.ProgramConfig;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramTask;
import com.maibaduoduo.logistics.deliveryman.task.translator.ProgramEventTranslator;
import org.springframework.stereotype.Component;

/**
 * ProgramEventPublisher.
 *
 * @author saas
 */
@Component
public class RushOrderEventPublisher {

     private ProgramConfig programConfig;
     public RushOrderEventPublisher(ProgramConfig programConfig){
         this.programConfig = programConfig;
     }

    /**
     * publish program event.
     * @param programTask
     * @param type
     */
    public void publishEvent(final ProgramTask programTask, final int type) {
        final RingBuffer<ProgramEvent> ringBuffer = programConfig.programConfig().getRingBuffer();
        ringBuffer.publishEvent(new ProgramEventTranslator(type), programTask);
    }

}
