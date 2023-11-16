/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.service.impl;

import com.maibaduoduo.store.dao.GoodsSnDao;
import com.maibaduoduo.store.entity.GoodsSnEntity;
import com.maibaduoduo.store.service.GoodsSnService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;


@Service("goodsSnService")
public class GoodsSnServiceImpl extends ServiceImpl<GoodsSnDao, GoodsSnEntity> implements GoodsSnService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsSnEntity> page = this.page(
                new Query<GoodsSnEntity>().getPage(params),
                new QueryWrapper<GoodsSnEntity>()
        );

        return new PageUtils(page);
    }

}