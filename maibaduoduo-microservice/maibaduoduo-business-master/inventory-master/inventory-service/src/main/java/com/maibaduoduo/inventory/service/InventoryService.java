/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.inventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.inventory.entity.InventoryEntity;
import java.util.Map;

/**
 * 中央仓库
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
public interface InventoryService extends IService<InventoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

