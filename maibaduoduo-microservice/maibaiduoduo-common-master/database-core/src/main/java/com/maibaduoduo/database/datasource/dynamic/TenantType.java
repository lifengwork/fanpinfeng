/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.database.datasource.dynamic;

public class TenantType extends TenantInfo {
    public String getTenantType() {
        if(this.getEmployee().equals(this.getTenantId())){
            return this.getTenantId();
        }
        return this.getEmployee();
    }
}
