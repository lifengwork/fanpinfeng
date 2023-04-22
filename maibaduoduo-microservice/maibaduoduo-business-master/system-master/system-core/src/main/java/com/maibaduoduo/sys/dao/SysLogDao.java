/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.maibaduoduo.com
 *
 * 版权所有，侵权必究！
 */

package com.maibaduoduo.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maibaduoduo.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author Mark lifengwork@yeah.net
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
