/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.app.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maibaduoduo.app.annotation.Login;
import com.maibaduoduo.app.annotation.LoginUser;
import com.maibaduoduo.app.service.AppUserService;
import com.maibaduoduo.common.form.LoginForm;
import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.configuration.utils.ValidatorUtils;
import com.maibaduoduo.jwt.model.AuthorizationInfo;
import com.maibaduoduo.sys.entity.SysUserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * APP登录授权
 */
@RestController
@RequestMapping("/app")
@Api("APP登录接口")
public class AppController{
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private JedisPool jedisPool;
    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form){
        ValidatorUtils.validateEntity(form);
        AuthorizationInfo authorizationInfo =null;
        try{
            authorizationInfo = appUserService.doLogin(form);
        }catch (Exception e){
            return R.error(e.getMessage());
        }
        return R.ok(JSON.toJSONString(authorizationInfo));
    }

    @Login
    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public R userInfo(@LoginUser SysUserEntity user){
        return R.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public R userInfo(@RequestParam("userId") String userId){
        return R.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public R notToken(){
        return R.ok().put("msg", "无需token也能访问。。。");
    }
}
