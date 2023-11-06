/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.task.translator;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramTask;

/**
 * ProgramEventTranslator.
 *
 * @author saas
 */
public class ProgramEventTranslator implements EventTranslatorOneArg<ProgramEvent, ProgramTask> {

    private int type;

    /**
     * Instantiates a new ProgramEventTranslator event translator.
     *
     * @param type the type
     */
    public ProgramEventTranslator(final int type) {
        this.type = type;
    }

    @Override
    public void translateTo(final ProgramEvent programEvent, final long l, final ProgramTask programTask) {
        programEvent.setProgramTask(programTask);
        programEvent.setType(type);
    }
}
