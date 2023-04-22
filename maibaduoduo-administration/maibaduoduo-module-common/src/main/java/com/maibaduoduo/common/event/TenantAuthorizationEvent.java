package com.maibaduoduo.common.event;

import java.io.Serializable;

public class TenantAuthorizationEvent extends  Event implements Serializable {
    protected Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TenantAuthorizationEvent(Object source) {
        super(source);
    }

}
