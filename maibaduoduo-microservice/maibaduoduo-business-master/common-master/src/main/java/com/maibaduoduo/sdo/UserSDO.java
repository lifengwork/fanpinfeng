/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.sdo;

import lombok.Data;

@Data
public class UserSDO {
    private Long userId;
    private String tenantId;
    private String username;
    private String mobile;
    private Integer status;
    private String email;
}
