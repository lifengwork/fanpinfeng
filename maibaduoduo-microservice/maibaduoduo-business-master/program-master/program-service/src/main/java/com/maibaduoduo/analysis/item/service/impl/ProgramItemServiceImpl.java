/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.analysis.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.exception.SaasException;
import com.maibaduoduo.configuration.utils.Query;
import com.maibaduoduo.analysis.item.dao.ProgramItemDao;
import com.maibaduoduo.analysis.item.entity.ProgramItemEntity;
import com.maibaduoduo.analysis.item.service.ProgramItemService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.maibaduoduo.configuration.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;

@Service("programItemService")
public class ProgramItemServiceImpl extends ServiceImpl<ProgramItemDao, ProgramItemEntity> implements ProgramItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProgramItemEntity> page = this.page(
                new Query<ProgramItemEntity>().getPage(params),
                new QueryWrapper<ProgramItemEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional(rollbackFor = SaasException.class)
    @Override
    public boolean save(ProgramItemEntity entity) throws SaasException {
        return super.save(entity);
    }

}