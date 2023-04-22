package com.maibaduoduo.common.event;

import com.maibaduoduo.common.utils.IdGen;
import org.springframework.context.ApplicationEvent;

/**
 * 基础事件类
 */
public class Event extends ApplicationEvent {
    public Event(Object source) {
        super(source);
    }

    public String getEventId() {
        return IdGen.SnowFlake();
    }
}
