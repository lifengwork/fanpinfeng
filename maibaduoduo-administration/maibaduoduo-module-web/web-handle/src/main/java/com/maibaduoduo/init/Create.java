/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.init;

import com.maibaduoduo.base.tenant.entity.SysTenant;

public interface Create {
    void create(SysTenant sysTenant) throws Exception;
}
