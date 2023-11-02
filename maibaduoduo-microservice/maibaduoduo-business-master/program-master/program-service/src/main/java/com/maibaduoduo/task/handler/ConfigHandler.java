/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.handler;

import com.google.common.collect.Lists;
import com.lmax.disruptor.dsl.Disruptor;
import com.maibaduoduo.configuration.SaasSpringContextUtil;
import com.maibaduoduo.task.config.ProgramConfig;
import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.program.Program;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * 任务处理器配置
 * @Auth saas
 * @Date 2023-5-13
 */
public class ConfigHandler extends ProgramConfig {

    public ConfigHandler(Program program) {
        super(program);
    }

    /**
     * 根据实际业务需要配置处理器
     * @param executor
     * @return
     */
    @Override
    protected Disruptor<ProgramEvent> configHandler(Executor executor) {
        List<EventHandler> eventHandlers = null;
        Map<String, EventHandler> eventHandlerMap = SaasSpringContextUtil.getBeansOfType(EventHandler.class);
        for (EventHandler eventHandler : eventHandlerMap.values()) {
            eventHandlers = Lists.newArrayList();
            for (int i = 0; i < MAX_THREAD; i++) {
                eventHandlers.add(eventHandler.programEventHandlerInit(program, executor));
            }
            //
            EventHandler[] consumers = new EventHandler[eventHandlers.size()];
            for (int i = 0; i < eventHandlers.size(); i++) {
                consumers[i] = eventHandlers.get(i);
            }
            disruptor.handleEventsWithWorkerPool(consumers);
        }
        return disruptor;
    }
}
