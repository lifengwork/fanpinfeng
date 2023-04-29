/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(String tenantId, String userName, Long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
