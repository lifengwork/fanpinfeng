/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maibaduoduo.app.service.AppUserService;
import com.maibaduoduo.common.form.LoginForm;
import com.maibaduoduo.configuration.exception.SaasException;
import com.maibaduoduo.configuration.utils.RedisUtils;
import com.maibaduoduo.database.datasource.utils.JwtUtils;
import com.maibaduoduo.jwt.TokenUtil;
import com.maibaduoduo.jwt.model.JwtUserInfo;
import com.maibaduoduo.sys.entity.SysUserEntity;
import com.maibaduoduo.validator.Assert;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Description: //TODO
 * @date: 2023/4/13 17:01
 * @Author: pm2022
 */
@Service
public class AppUserServiceImpl extends AppUserService {
    @Value("${maibaduoduo.jwt.header}")
    private String USER_KEY;
    @Autowired
    private RedisUtils redisUtils;

   /* @Autowired
    private JwtUtils jwtUtils;*/
   @Autowired
   private TokenUtil tokenUtil;

    @Override
    public SysUserEntity queryByMobile(String mobile) {
        return this.getOne(new QueryWrapper<SysUserEntity>().eq("mobile", mobile));
    }

    @Override
    public long login(LoginForm form) {
        SysUserEntity userEntity = this.queryByMobile(form.getMobile());
        Assert.isNull(userEntity, "手机号或密码错误");
        if(!userEntity.getPassword().equals(new Sha256Hash(form.getPassword(), userEntity.getSalt()).toHex())){
            throw new SaasException("手机号或密码错误");
        }
        return userEntity.getUserId();
    }

    /**
     * 返回TOKEN
     * @param form
     * @return
     */
    @Override
    public String doLogin(LoginForm form) {
        SysUserEntity userEntity = this.queryByMobile(form.getMobile());
        Assert.isNull(userEntity, "手机号或密码错误");
        if(!userEntity.getPassword().equals(new Sha256Hash(form.getPassword(), userEntity.getSalt()).toHex())){
            throw new SaasException("用户密码错误");
        }
        //return jwtUtils.generateToken(userEntity.getUsername(),userEntity.getTenantId());
        return tokenUtil.createAuthInfo(new JwtUserInfo().setValue(userEntity.getUserId(),userEntity.getMobile(),
                userEntity.getUsername(), StringUtils.isEmpty(userEntity.getTenantId())?"0":userEntity.getTenantId()),null)
                .getToken();
    }
}
@Data
class SysUserCache{
    private Long userId;
    private String tenantId;
    private String userName;
    private String mobile;
}