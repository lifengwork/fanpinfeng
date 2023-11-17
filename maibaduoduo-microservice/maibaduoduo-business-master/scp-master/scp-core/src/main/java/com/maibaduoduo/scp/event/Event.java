/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.vehicle.event;

import com.maibaduoduo.configuration.utils.IdGen;
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
