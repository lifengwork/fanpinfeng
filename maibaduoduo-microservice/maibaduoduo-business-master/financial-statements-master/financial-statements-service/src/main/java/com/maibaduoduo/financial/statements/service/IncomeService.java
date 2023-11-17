/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.financial.statements.entity.IncomeEntity;

import java.util.Map;

/**
 * 利润表
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:12:24
 */
public interface IncomeService extends IService<IncomeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

