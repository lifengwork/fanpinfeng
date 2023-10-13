/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maibaduoduo.logistics.entity.LogisticsVehicleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-06-25 09:24:11
 */
@Mapper
public interface LogisticsVehicleDao extends BaseMapper<LogisticsVehicleEntity> {
	
}
