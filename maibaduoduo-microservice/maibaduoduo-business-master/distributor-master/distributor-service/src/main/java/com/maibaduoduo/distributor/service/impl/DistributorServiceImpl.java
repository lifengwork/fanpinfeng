/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.distributor.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.distributor.dao.DistributorDao;
import com.maibaduoduo.distributor.entity.DistributorEntity;
import com.maibaduoduo.distributor.service.DistributorService;


@Service("distributorService")
public class DistributorServiceImpl extends ServiceImpl<DistributorDao, DistributorEntity> implements DistributorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DistributorEntity> page = this.page(
                new Query<DistributorEntity>().getPage(params),
                new QueryWrapper<DistributorEntity>()
        );

        return new PageUtils(page);
    }

}