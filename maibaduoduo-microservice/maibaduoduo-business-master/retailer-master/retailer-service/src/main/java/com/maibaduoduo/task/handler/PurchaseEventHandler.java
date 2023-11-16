/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.handler;

import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.event.ProgramEventType;
import com.maibaduoduo.task.event.ProgramTask;
import com.maibaduoduo.task.handler.base.MainEventHandler;
import com.maibaduoduo.task.program.Program;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * 采购物品事件处理器
 * @author saas
 */
@Component
public class PurchaseEventHandler extends MainEventHandler {
    private Program program;
    private Executor executor;

    /**
     * 发送MQ消息到订单服务
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
    public void beforeExecute() {

    }

    @Override
    public void afterExecute() {

    }
}
