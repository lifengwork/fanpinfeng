/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.maibaduoduo.com
 *
 * 版权所有，侵权必究！
 */

package com.maibaduoduo.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录用户信息
 *
 * @author Mark lifengwork@yeah.net
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
