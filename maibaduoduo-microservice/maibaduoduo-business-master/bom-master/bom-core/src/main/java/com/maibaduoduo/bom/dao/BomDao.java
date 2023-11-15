/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.bom.dao;

import com.maibaduoduo.bom.entity.BomEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品物料清单
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-14 12:06:49
 */
@Mapper
public interface BomDao extends BaseMapper<BomEntity> {
	
}
