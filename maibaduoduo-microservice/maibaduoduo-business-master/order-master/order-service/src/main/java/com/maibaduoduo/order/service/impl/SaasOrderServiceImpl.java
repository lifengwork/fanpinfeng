/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.order.service.SaasOrderService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;
import com.maibaduoduo.order.dao.SaasOrderDao;
import com.maibaduoduo.order.entity.SaasOrderEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("saasOrderService")
public class SaasOrderServiceImpl extends ServiceImpl<SaasOrderDao, SaasOrderEntity> implements SaasOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SaasOrderEntity> page = this.page(
                new Query<SaasOrderEntity>().getPage(params),
                new QueryWrapper<SaasOrderEntity>()
        );

        return new PageUtils(page);
    }

}