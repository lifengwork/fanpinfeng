/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.finance.entity.FinancialEnsureCreditEntity;

import java.util.Map;

/**
 * 担保人征信
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:01:07
 */
public interface FinancialEnsureCreditService extends IService<FinancialEnsureCreditEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

