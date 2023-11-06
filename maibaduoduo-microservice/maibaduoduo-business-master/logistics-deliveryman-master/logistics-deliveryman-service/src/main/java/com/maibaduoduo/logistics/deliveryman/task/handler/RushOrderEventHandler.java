/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.task.handler;

import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEventType;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramTask;
import com.maibaduoduo.logistics.deliveryman.task.program.Program;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * 加急订单处理
 * @author saas
 */
@Component
public class RushOrderEventHandler extends MainEventHandler {
    private Program program;
    private Executor executor;
    /**
     * 更新订单状态（备货中）
     * @param programEvent
     */
    @Override
    public void doHandle(final ProgramEvent programEvent) {
        executor.execute(() -> {
            if (programEvent.getType() == ProgramEventType.EXECUTE.getCode()) {
                logger.info("Event Type is EXECUTE，{}",programEvent.getType());
                ProgramTask programTask = programEvent.getProgramTask();
                program.execute(programTask.getExecuteObject());
            }
            programEvent.clear();
        });
    }

    @Override
    public MainEventHandler programEventHandlerInit(Program program, Executor executor) {
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
