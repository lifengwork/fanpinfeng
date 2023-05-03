/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.configuration.utils.TokenGenerator;
import com.maibaduoduo.jwt.TokenUtil;
import com.maibaduoduo.jwt.model.AuthorizationInfo;
import com.maibaduoduo.jwt.model.JwtUserInfo;
import com.maibaduoduo.service.SysUserTokenService;
import com.maibaduoduo.sys.dao.SysUserTokenDao;
import com.maibaduoduo.sys.entity.SysUserTokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.Date;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;
	@Autowired
	private JedisPool jedisPool;

	@Autowired
	private TokenUtil tokenUtil;

	@Override
	public R createToken(String tenantId, String userName, Long userId) {

		AuthorizationInfo authorizationInfo = tokenUtil.createAuthInfo(new JwtUserInfo().setValue(userId,null,userName,tenantId),null);
		String token = authorizationInfo.getToken();
//		//当前时间
		Date now = new Date();
//		//过期时间
//		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserTokenEntity tokenEntity = this.getById(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setTenantId(tenantId);
			tokenEntity.setExpireTime(authorizationInfo.getExpiration());

			//保存token
			this.save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(authorizationInfo.getExpiration());

			//更新token
			this.updateById(tokenEntity);
		}

		R r = R.ok().put("token", token).put("expire", EXPIRE);
		return r;
	}

	@Override
	public void logout(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//修改token
		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		this.updateById(tokenEntity);
	}
}
