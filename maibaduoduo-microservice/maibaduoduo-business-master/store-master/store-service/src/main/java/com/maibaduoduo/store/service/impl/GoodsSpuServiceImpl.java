/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.service.impl;

import com.maibaduoduo.store.dao.GoodsSpuDao;
import com.maibaduoduo.store.entity.GoodsSpuEntity;
import com.maibaduoduo.store.service.GoodsSpuService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;


@Service("goodsSpuService")
public class GoodsSpuServiceImpl extends ServiceImpl<GoodsSpuDao, GoodsSpuEntity> implements GoodsSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsSpuEntity> page = this.page(
                new Query<GoodsSpuEntity>().getPage(params),
                new QueryWrapper<GoodsSpuEntity>()
        );

        return new PageUtils(page);
    }

}