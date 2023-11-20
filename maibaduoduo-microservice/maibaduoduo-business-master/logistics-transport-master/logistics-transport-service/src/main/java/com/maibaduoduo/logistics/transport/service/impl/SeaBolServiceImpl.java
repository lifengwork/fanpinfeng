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

import com.maibaduoduo.logistics.transport.dao.SeaBolDao;
import com.maibaduoduo.logistics.transport.entity.SeaBolEntity;
import com.maibaduoduo.logistics.transport.service.SeaBolService;


@Service("seaBolService")
public class SeaBolServiceImpl extends ServiceImpl<SeaBolDao, SeaBolEntity> implements SeaBolService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeaBolEntity> page = this.page(
                new Query<SeaBolEntity>().getPage(params),
                new QueryWrapper<SeaBolEntity>()
        );

        return new PageUtils(page);
    }

}