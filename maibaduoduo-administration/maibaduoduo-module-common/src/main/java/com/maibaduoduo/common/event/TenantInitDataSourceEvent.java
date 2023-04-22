package com.maibaduoduo.common.event;

import java.io.Serializable;

public class TenantInitDataSourceEvent extends Event implements Serializable {
    public TenantInitDataSourceEvent(Object source) {
        super(source);
    }
}
