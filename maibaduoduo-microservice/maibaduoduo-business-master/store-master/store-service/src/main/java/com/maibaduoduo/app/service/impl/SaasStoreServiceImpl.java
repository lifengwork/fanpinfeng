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
import com.maibaduoduo.app.service.SaasStoreService;
import com.maibaduoduo.common.utils.PageUtils;
import com.maibaduoduo.common.utils.Query;
import com.maibaduoduo.store.dao.SaasStoreDao;
import com.maibaduoduo.store.entity.SaasStoreEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("saasStoreService")
public class SaasStoreServiceImpl extends ServiceImpl<SaasStoreDao, SaasStoreEntity> implements SaasStoreService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SaasStoreEntity> page = this.page(
                new Query<SaasStoreEntity>().getPage(params),
                new QueryWrapper<SaasStoreEntity>()
        );

        return new PageUtils(page);
    }

}