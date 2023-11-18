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

import com.maibaduoduo.scp.dao.ScpInventoryDao;
import com.maibaduoduo.scp.entity.ScpInventoryEntity;
import com.maibaduoduo.scp.service.ScpInventoryService;


@Service("scpInventoryService")
public class ScpInventoryServiceImpl extends ServiceImpl<ScpInventoryDao, ScpInventoryEntity> implements ScpInventoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ScpInventoryEntity> page = this.page(
                new Query<ScpInventoryEntity>().getPage(params),
                new QueryWrapper<ScpInventoryEntity>()
        );

        return new PageUtils(page);
    }

}