/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.task.config;

import com.lmax.disruptor.dsl.Disruptor;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import com.maibaduoduo.logistics.deliveryman.task.handler.*;
import com.maibaduoduo.logistics.deliveryman.task.program.Program;
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
    private CreateDeliverEventHandler createDeliverEventHandler;
    @Autowired
    private RushOrderEventHandler rushOrderEventHandler;
    @Autowired
    private SendInfoEventHandler sendInfoEventHandler;
    @Autowired
    private StoreEventHandler storeEventHandler;
    @Autowired
    private VehicleEventHandler vehicleEventHandler;

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
        disruptor.handleEventsWithWorkerPool(rushOrderEventHandler.programEventHandlerInit(program, executor)).
                then(vehicleEventHandler).
                then(createDeliverEventHandler).
                then(sendInfoEventHandler,storeEventHandler);
        return disruptor;
    }
}
