/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.retailer.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.retailer.dao.RetailerDao;
import com.maibaduoduo.retailer.entity.RetailerEntity;
import com.maibaduoduo.retailer.service.RetailerService;


@Service("retailerService")
public class RetailerServiceImpl extends ServiceImpl<RetailerDao, RetailerEntity> implements RetailerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RetailerEntity> page = this.page(
                new Query<RetailerEntity>().getPage(params),
                new QueryWrapper<RetailerEntity>()
        );

        return new PageUtils(page);
    }

}