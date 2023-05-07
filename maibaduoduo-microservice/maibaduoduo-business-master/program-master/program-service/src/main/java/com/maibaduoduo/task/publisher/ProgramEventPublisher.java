/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.publisher;

import com.lmax.disruptor.RingBuffer;

import com.maibaduoduo.task.config.ProgramConfig;
import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.event.ProgramTask;
import com.maibaduoduo.task.translator.ProgramEventTranslator;
import org.springframework.stereotype.Component;
/**
 * ProgramEventPublisher.
 *
 * @author saas
 */
@Component
public class ProgramEventPublisher {

     private ProgramConfig programConfig;
     public ProgramEventPublisher(ProgramConfig programConfig){
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
