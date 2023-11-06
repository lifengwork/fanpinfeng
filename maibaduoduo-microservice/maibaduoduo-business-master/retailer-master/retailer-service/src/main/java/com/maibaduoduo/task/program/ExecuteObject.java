/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.program;

import com.maibaduoduo.order.entity.SaasOrderEntity;
import lombok.Data;

import java.io.Serializable;

public class ExecuteObject implements Serializable {
    private Long executeId;
    private SaasOrderEntity saasOrderEntity;

    public Long getExecuteId() {
        return executeId;
    }

    public void setExecuteId(Long executeId) {
        this.executeId = executeId;
    }

    public SaasOrderEntity getSaasOrderEntity() {
        return saasOrderEntity;
    }

    public void setSaasOrderEntity(SaasOrderEntity saasOrderEntity) {
        this.saasOrderEntity = saasOrderEntity;
    }
}
