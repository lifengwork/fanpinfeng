/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.configuration.utils;

import com.maibaduoduo.configuration.exception.SaasException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author Mark lifengwork@yeah.net
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new SaasException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new SaasException(message);
        }
    }
}
