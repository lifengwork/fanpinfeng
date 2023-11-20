/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.transport.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.logistics.transport.dao.InlandBolDao;
import com.maibaduoduo.logistics.transport.entity.InlandBolEntity;
import com.maibaduoduo.logistics.transport.service.InlandBolService;


@Service("inlandBolService")
public class InlandBolServiceImpl extends ServiceImpl<InlandBolDao, InlandBolEntity> implements InlandBolService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InlandBolEntity> page = this.page(
                new Query<InlandBolEntity>().getPage(params),
                new QueryWrapper<InlandBolEntity>()
        );

        return new PageUtils(page);
    }

}