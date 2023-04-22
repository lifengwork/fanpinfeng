package com.maibaduoduo.common.event.config;

import com.maibaduoduo.common.event.Event;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public  class CustomizeEventListener implements ApplicationListener<Event> {
    /**
     * 事件监听器默认开启异步
     * @param event
     */
    @Async
    @Override
    public void onApplicationEvent(Event event) {

    }

}
