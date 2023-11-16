/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.purchase.entity.PurchaseEntity;

import java.util.Map;

/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 21:11:37
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean save(PurchaseEntity entity);
}

