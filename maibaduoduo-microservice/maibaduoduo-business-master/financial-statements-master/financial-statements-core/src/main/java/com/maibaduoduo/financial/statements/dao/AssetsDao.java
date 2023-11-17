/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maibaduoduo.financial.statements.entity.AssetsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产表
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:12:24
 */
@Mapper
public interface AssetsDao extends BaseMapper<AssetsEntity> {
	
}
