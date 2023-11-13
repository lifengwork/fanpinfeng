/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.program;
import java.io.Serializable;

public class ExecuteObject implements Serializable {
    private Long executeId;
    private EventData eventData;

    public Long getExecuteId() {
        return executeId;
    }

    public ExecuteObject setExecuteId(Long executeId) {
        this.executeId = executeId;
        return this;
    }

    public EventData getEventData() {
        return eventData;
    }

    public ExecuteObject setEventData(EventData eventData) {
        this.eventData = eventData;
        return this;
    }

}
