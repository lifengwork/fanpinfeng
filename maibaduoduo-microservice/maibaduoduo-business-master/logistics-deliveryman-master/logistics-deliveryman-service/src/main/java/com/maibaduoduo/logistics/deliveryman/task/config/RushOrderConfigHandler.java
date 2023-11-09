/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.task.config;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.dsl.Disruptor;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramTask;
import com.maibaduoduo.logistics.deliveryman.task.handler.*;
import com.maibaduoduo.logistics.deliveryman.task.handler.base.NoPersistenceEventHandler;
import com.maibaduoduo.logistics.deliveryman.task.program.EventData;
import com.maibaduoduo.logistics.deliveryman.task.program.ExecuteObject;
import com.maibaduoduo.logistics.deliveryman.task.program.Program;
import com.maibaduoduo.logistics.deliveryman.task.publisher.RushOrderEventPublisher;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Executor;

/**
 * 任务处理器配置
 *
 * @Auth saas
 * @Date 2023-5-13
 */
@Component
public class RushOrderConfigHandler extends ProgramConfig {
    protected Log log = LogFactory.getLog(this.getClass());
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
    @Autowired
    private NoPersistenceEventHandler noPersistenceEventHandler;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RushOrderEventPublisher rushOrderEventPublisher;

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
                then(sendInfoEventHandler,storeEventHandler).then(noPersistenceEventHandler);
        return disruptor;
    }

    @Override
    protected void afterProcessor() {
        Map<Object,Object> map = redisTemplate.boundHashOps(EventContants.E_RAWKEY).entries();
        log.warn("--事件恢复--" + EventContants.E_RAWKEY + "," + map.size());
        if(!map.isEmpty()){
            map.forEach((key,value)->{
                rushOrderEventPublisher.publishEvent(new ProgramTask()
                        .setExecuteObject(new ExecuteObject().
                                setEventData(new EventData().
                                        setContent(JSON.toJSONString(value)))
                                .setExecuteId((Long)key)), 0);
            });
        }
    }
}
