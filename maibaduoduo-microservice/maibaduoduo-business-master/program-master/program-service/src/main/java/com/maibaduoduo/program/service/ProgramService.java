/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.exception.SaasException;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.program.entity.ProgramEntity;

import java.util.Map;

/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-05-06 20:24:36
 */
public interface ProgramService extends IService<ProgramEntity> {

    PageUtils queryPage(Map<String, Object> params);
    boolean save(ProgramEntity entity) throws SaasException;
}

