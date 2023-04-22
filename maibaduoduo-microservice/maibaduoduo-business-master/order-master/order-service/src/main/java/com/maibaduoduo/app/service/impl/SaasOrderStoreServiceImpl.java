/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.app.service.SaasOrderStoreService;
import com.maibaduoduo.common.utils.PageUtils;
import com.maibaduoduo.common.utils.Query;
import com.maibaduoduo.common.utils.R;
import com.maibaduoduo.order.dao.SaasOrderStoreDao;
import com.maibaduoduo.order.entity.SaasOrderStoreEntity;
import com.maibaduoduo.store.entity.SaasStoreEntity;
import com.maibaduoduo.store.facade.api.StoreFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("saasOrderStoreService")
public class SaasOrderStoreServiceImpl extends ServiceImpl<SaasOrderStoreDao, SaasOrderStoreEntity> implements SaasOrderStoreService {

    @Autowired
    private StoreFacade storeFacade;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SaasOrderStoreEntity> page = this.page(
                new Query<SaasOrderStoreEntity>().getPage(params),
                new QueryWrapper<SaasOrderStoreEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public SaasStoreEntity querySassStoreEntity(Long orderId) {
        this.list();
        SaasStoreEntity saasStoreEntity = new SaasStoreEntity();
        saasStoreEntity.setId(orderId);
        R r = storeFacade.info(orderId);
        saasStoreEntity.setStoreDescription(r.toString());
        return saasStoreEntity;
    }

}