/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.scp.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.scp.dao.ScpTransportDao;
import com.maibaduoduo.scp.entity.ScpTransportEntity;
import com.maibaduoduo.scp.service.ScpTransportService;


@Service("scpTransportService")
public class ScpTransportServiceImpl extends ServiceImpl<ScpTransportDao, ScpTransportEntity> implements ScpTransportService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ScpTransportEntity> page = this.page(
                new Query<ScpTransportEntity>().getPage(params),
                new QueryWrapper<ScpTransportEntity>()
        );

        return new PageUtils(page);
    }

}