/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.maibaduoduo.com
 *
 * 版权所有，侵权必究！
 */

package com.maibaduoduo.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author Mark lifengwork@yeah.net
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
