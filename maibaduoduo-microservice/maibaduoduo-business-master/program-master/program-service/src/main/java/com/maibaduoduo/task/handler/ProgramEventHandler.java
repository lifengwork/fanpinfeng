/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.handler;

import com.lmax.disruptor.WorkHandler;
import com.maibaduoduo.program.service.ProgramService;
import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.event.ProgramEventType;

import java.util.concurrent.Executor;

/**
 * ProgramEventHandler.
 * @author saas
 */
public class ProgramEventHandler implements WorkHandler<ProgramEvent> {

    private final ProgramService programService;

    private Executor executor;

    public ProgramEventHandler(final ProgramService programService,
                               final Executor executor) {
        this.programService = programService;
        this.executor = executor;
    }

    /**
     *
     * @param programEvent
     */
    @Override
    public void onEvent(final ProgramEvent programEvent) {
        executor.execute(() -> {
            if (programEvent.getType() == ProgramEventType.SAVE.getCode()) {
                //TODO
            } else if (programEvent.getType() == ProgramEventType.EXECUTE.getCode()) {
                //TODO
            } else if (programEvent.getType() == ProgramEventType.EXECUTE_STATUS.getCode()) {
                //TODO
            } else if (programEvent.getType() == ProgramEventType.EXECUTE_FAIR.getCode()) {
                //TODO
            }
            programEvent.clear();
        });
    }
}
