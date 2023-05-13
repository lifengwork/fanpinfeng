/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.supplier.service.impl;

import com.maibaduoduo.supplier.service.SupplierService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.supplier.dao.SupplierDao;
import com.maibaduoduo.supplier.entity.SupplierEntity;


@Service("supplierService")
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, SupplierEntity> implements SupplierService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SupplierEntity> page = this.page(
                new Query<SupplierEntity>().getPage(params),
                new QueryWrapper<SupplierEntity>()
        );

        return new PageUtils(page);
    }

}