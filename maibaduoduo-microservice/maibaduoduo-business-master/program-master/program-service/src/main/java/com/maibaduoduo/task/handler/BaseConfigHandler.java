/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.handler;

import com.lmax.disruptor.dsl.Disruptor;
import com.maibaduoduo.configuration.SaasSpringContextUtil;
import com.maibaduoduo.task.config.ProgramConfig;
import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.program.Program;

import java.util.Map;
import java.util.concurrent.Executor;

/**
 * 任务处理器配置
 *
 * @Auth saas
 * @Date 2023-5-13
 */
public class BaseConfigHandler extends ProgramConfig {
    protected AfterEventHandler[] afterEventHandlers = new AfterEventHandler[10];

    public BaseConfigHandler(Program program) {
        super(program);
    }

    /**
     * 根据实际业务需要配置处理器
     *
     * @param executor
     * @return
     */
    @Override
    protected Disruptor<ProgramEvent> configHandler(Executor executor) {
        MainEventHandler mainEventHandler = SaasSpringContextUtil.getBean(MainEventHandler.class);
        if (afterEventHandlers.length > 0) {
            disruptor.handleEventsWithWorkerPool(mainEventHandler.programEventHandlerInit(program, executor)).then(afterEventHandlers);
        } else {
            disruptor.handleEventsWithWorkerPool(mainEventHandler.programEventHandlerInit(program, executor));
        }
        return disruptor;
    }
}
