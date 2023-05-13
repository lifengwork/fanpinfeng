/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.supplier.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.supplier.dao.DataAnalysisDao;
import com.maibaduoduo.supplier.entity.DataAnalysisEntity;
import com.maibaduoduo.supplier.service.DataAnalysisService;


@Service("dataAnalysisService")
public class DataAnalysisServiceImpl extends ServiceImpl<DataAnalysisDao, DataAnalysisEntity> implements DataAnalysisService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DataAnalysisEntity> page = this.page(
                new Query<DataAnalysisEntity>().getPage(params),
                new QueryWrapper<DataAnalysisEntity>()
        );

        return new PageUtils(page);
    }

}