/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.supplier.service.impl;

import com.maibaduoduo.configuration.exception.SaasException;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.supplier.dao.ProgramDao;
import com.maibaduoduo.supplier.entity.ProgramEntity;
import com.maibaduoduo.supplier.service.ProgramService;
import org.springframework.transaction.annotation.Transactional;


@Service("programService")
public class ProgramServiceImpl extends ServiceImpl<ProgramDao, ProgramEntity> implements ProgramService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProgramEntity> page = this.page(
                new Query<ProgramEntity>().getPage(params),
                new QueryWrapper<ProgramEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional(rollbackFor = SaasException.class)
    @Override
    public boolean save(ProgramEntity entity) throws SaasException {
        return super.save(entity);
    }

}