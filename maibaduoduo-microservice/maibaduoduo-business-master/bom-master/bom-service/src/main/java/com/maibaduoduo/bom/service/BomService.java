/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.bom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.bom.entity.BomEntity;

import java.util.Map;

/**
 * 产品物料清单
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-14 12:06:49
 */
public interface BomService extends IService<BomEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

