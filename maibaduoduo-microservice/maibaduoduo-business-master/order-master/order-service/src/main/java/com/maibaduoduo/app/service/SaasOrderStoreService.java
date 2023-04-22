/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.common.utils.PageUtils;
import com.maibaduoduo.order.entity.SaasOrderStoreEntity;
import com.maibaduoduo.store.entity.SaasStoreEntity;

import java.util.Map;

/**
 * 
 *
 * @author lf
 * @email lifengwork@yeah.net
 * @date 2023-04-16 13:00:02
 */
public interface SaasOrderStoreService extends IService<SaasOrderStoreEntity> {

    PageUtils queryPage(Map<String, Object> params);
    SaasStoreEntity querySassStoreEntity(Long orderId);
}

