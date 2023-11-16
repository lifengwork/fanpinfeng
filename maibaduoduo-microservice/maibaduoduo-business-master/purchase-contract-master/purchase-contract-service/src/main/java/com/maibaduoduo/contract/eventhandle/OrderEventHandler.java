package com.maibaduoduo.contract.eventhandle;


import com.alibaba.fastjson.JSON;
import com.maibaduoduo.event.ContractEvent;
import com.maibaduoduo.order.facade.api.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
@Async
public class OrderEventHandler implements ApplicationListener<ContractEvent> {
    @Autowired
    private OrderFacade orderFacade;
    @Override
    public void onApplicationEvent(ContractEvent storeEvent) {
        orderFacade.settlement(JSON.toJSONString(storeEvent.getSource()));
    }
}
