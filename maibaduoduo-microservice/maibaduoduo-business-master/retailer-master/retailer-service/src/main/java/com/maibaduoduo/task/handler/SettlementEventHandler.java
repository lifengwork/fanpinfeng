package com.maibaduoduo.task.handler;

import com.lmax.disruptor.EventHandler;
import com.maibaduoduo.task.event.ProgramEvent;
import org.springframework.stereotype.Component;

@Component
public class SettlementEventHandler implements EventHandler<ProgramEvent> {
    @Override
    public void onEvent(ProgramEvent programEvent, long l, boolean b) throws Exception {
        /**
         * TODO 订单金额结算
         * 主要根据加急订单的权重计算对应的配送费，从心计算订单实付金额
         */
    }
}