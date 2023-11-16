/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.store.entity.StoreInventoryEntity;

import java.util.Map;

/**
 * 仓储库存
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
public interface StoreInventoryService extends IService<StoreInventoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

