/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.service.impl;

import com.maibaduoduo.store.dao.GoodsSkuDao;
import com.maibaduoduo.store.entity.GoodsSkuEntity;
import com.maibaduoduo.store.service.GoodsSkuService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;



@Service("goodsSkuService")
public class GoodsSkuServiceImpl extends ServiceImpl<GoodsSkuDao, GoodsSkuEntity> implements GoodsSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsSkuEntity> page = this.page(
                new Query<GoodsSkuEntity>().getPage(params),
                new QueryWrapper<GoodsSkuEntity>()
        );

        return new PageUtils(page);
    }

}