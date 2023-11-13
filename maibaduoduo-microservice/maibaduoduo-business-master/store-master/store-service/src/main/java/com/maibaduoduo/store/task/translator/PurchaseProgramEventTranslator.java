/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.translator;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.maibaduoduo.store.task.event.ProgramEvent;
import com.maibaduoduo.store.task.event.ProgramTask;
import com.maibaduoduo.store.task.event.PurchaseProgramEvent;

/**
 * ProgramEventTranslator.
 *
 * @author saas
 */
public class PurchaseProgramEventTranslator implements EventTranslatorOneArg<PurchaseProgramEvent, ProgramTask> {

    private int type;

    /**
     * Instantiates a new ProgramEventTranslator event translator.
     *
     * @param type the type
     */
    public PurchaseProgramEventTranslator(final int type) {
        this.type = type;
    }

    @Override
    public void translateTo(final PurchaseProgramEvent programEvent, final long l, final ProgramTask programTask) {
        programEvent.setProgramTask(programTask);
        programEvent.setType(type);
    }
}
