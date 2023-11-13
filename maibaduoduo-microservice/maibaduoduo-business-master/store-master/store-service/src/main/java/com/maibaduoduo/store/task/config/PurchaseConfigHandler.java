/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.config;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.dsl.Disruptor;
import com.maibaduoduo.store.task.event.ProgramTask;
import com.maibaduoduo.store.task.event.PurchaseProgramEvent;
import com.maibaduoduo.store.task.handler.*;
import com.maibaduoduo.store.task.handler.base.PurchaseNoPersistenceEventHandler;
import com.maibaduoduo.store.task.program.EventData;
import com.maibaduoduo.store.task.program.ExecuteObject;
import com.maibaduoduo.store.task.program.Program;
import com.maibaduoduo.store.task.publisher.PurchaseEventPublisher;
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
public class PurchaseConfigHandler extends PurchaseProgramConfig {
    protected Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private PurchaseEventPublisher purchaseEventPublisher;
    @Autowired
    private PurchaseNoPersistenceEventHandler noPersistenceEventHandler;
    @Autowired
    private TruckLoadingEventHandler truckLoadingEventHandler;
    @Autowired
    private UpdateOrderEventHandler updateOrderEventHandler;
    @Autowired
    private PickingEventHandler pickingEventHandler;
    @Autowired
    private InventoryEventHandler inventoryventHandler;

    public PurchaseConfigHandler(Program program) {
        super(program);
    }

    /**
     * 根据实际业务需要配置处理器
     * * 5拣货,打包出库
     * * 6装车并修改配送单以及订单状态,生成运输单，订单状态为已发货，库存在途。
     *
     * @param executor
     * @return
     */
    @Override
    protected Disruptor<PurchaseProgramEvent> configHandler(Executor executor) {
        disruptor.handleEventsWithWorkerPool(updateOrderEventHandler.programEventHandlerInit(program, executor)).
                then(pickingEventHandler).
                then(inventoryventHandler).
                then(truckLoadingEventHandler).
                then(noPersistenceEventHandler);
        return disruptor;
    }

    @Override
    protected void afterProcessor() {
        Map<Object, Object> map = redisTemplate.boundHashOps(EventContants.E_RAWKEY).entries();
        log.warn("--事件恢复--" + EventContants.E_RAWKEY + "," + map.size());
        if (!map.isEmpty()) {
            map.forEach((key, value) -> {
                purchaseEventPublisher.publishEvent(new ProgramTask()
                        .setExecuteObject(new ExecuteObject().
                                setEventData(new EventData().
                                        setContent(JSON.toJSONString(value)))
                                .setExecuteId((Long) key)), 0);
            });
        }
    }
}
