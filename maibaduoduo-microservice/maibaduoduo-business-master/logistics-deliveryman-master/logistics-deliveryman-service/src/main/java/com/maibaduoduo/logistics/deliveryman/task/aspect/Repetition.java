/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.task.aspect;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author Mark lifengwork@yeah.net
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Repetition {

	String value() default "";
}
