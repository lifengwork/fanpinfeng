/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.bom.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.bom.dao.BomDao;
import com.maibaduoduo.bom.entity.BomEntity;
import com.maibaduoduo.bom.service.BomService;


@Service("bomService")
public class BomServiceImpl extends ServiceImpl<BomDao, BomEntity> implements BomService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BomEntity> page = this.page(
                new Query<BomEntity>().getPage(params),
                new QueryWrapper<BomEntity>()
        );

        return new PageUtils(page);
    }

}