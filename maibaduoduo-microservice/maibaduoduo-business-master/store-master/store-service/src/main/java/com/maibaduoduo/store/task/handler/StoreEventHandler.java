package com.maibaduoduo.store.task.handler;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.inventory.entity.InventoryEntity;
import com.maibaduoduo.inventory.facade.api.InventoryFacade;
import com.maibaduoduo.task.aspect.Repetition;
import com.maibaduoduo.task.event.ProgramEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 选择就近仓库
 */
@Component
public class StoreEventHandler implements EventHandler<ProgramEvent> {
    @Autowired
    private InventoryFacade inventoryFacade;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Repetition
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO
         * 1选择就近仓库
         *
         */
        Map<String, Object> inventory = new HashMap<String, Object>();
        InventoryEntity inventoryEntity = (InventoryEntity) JSON.parseObject(String.valueOf(inventoryFacade.
                judgeWarehouse(inventory).get("inventory")), InventoryEntity.class);
        programEvent.getProgramTask().getExecuteObject().getEventData().setStoreId(inventoryEntity.getStoreId());
    }
}