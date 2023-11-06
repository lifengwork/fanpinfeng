/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.config;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.maibaduoduo.configuration.SaasSpringContextUtil;
import com.maibaduoduo.task.event.ProgramEvent;
import com.maibaduoduo.task.handler.MainEventHandler;
import com.maibaduoduo.task.handler.OrderEventHandler;
import com.maibaduoduo.task.handler.SetCacheEventHandler;
import com.maibaduoduo.task.handler.SettlementEventHandler;
import com.maibaduoduo.task.program.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * 任务处理器配置
 *
 * @Auth saas
 * @Date 2023-5-13
 */
@Component
public class RushOrderConfigHandler extends ProgramConfig {
    @Autowired
    private OrderEventHandler orderEventHandler;
    @Autowired
    private SettlementEventHandler settlementEventHandler;
    @Autowired
    private SetCacheEventHandler setCacheEventHandler;

    public RushOrderConfigHandler(Program program) {
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
        disruptor.handleEventsWithWorkerPool(orderEventHandler.programEventHandlerInit(program, executor))
                .then(settlementEventHandler).then(setCacheEventHandler);
        return disruptor;
    }
}
