/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.handler;

import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.event.ProgramEventType;
import com.maibaduoduo.task.event.ProgramTask;
import com.maibaduoduo.task.program.ExecuteObject;
import com.maibaduoduo.task.program.Program;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * ProgramEventHandler.
 * @author saas
 */
@Component
public class ProgramEventHandler extends EventHandler {
    private Program program;
    private Executor executor;
    /**
     * 处理具体任务
     * @param programEvent
     */
    @Override
    public void doHandle(final ProgramEvent programEvent) {
        executor.execute(() -> {
            if (programEvent.getType() == ProgramEventType.EXECUTE.getCode()) {
                logger.info("Event Type is EXECUTE");
                program.execute(new ExecuteObject());
            } else if (programEvent.getType() == ProgramEventType.EXECUTE_STATUS.getCode()) {
                logger.info("Event Type is EXECUTE_STATUS");
                program.execute(new ExecuteObject());
            } else{
                logger.info("Event Type is null");
            }
            programEvent.clear();
        });
    }

    @Override
    public EventHandler programEventHandlerInit(Program program, Executor executor) {
        this.program = program;
        this.executor = executor;
        return this;
    }

    @Override
    void beforeExecute() {

    }

    @Override
    void afterExecute() {

    }
}
