/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.maibaduoduo.com
 *
 * 版权所有，侵权必究！
 */

package com.maibaduoduo.common.form;

import lombok.Data;

/**
 * 登录表单
 *
 * @author Mark lifengwork@yeah.net
 */
@Data
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
