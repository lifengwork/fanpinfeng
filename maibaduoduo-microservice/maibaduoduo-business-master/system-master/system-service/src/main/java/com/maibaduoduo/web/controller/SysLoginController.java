/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maibaduoduo.common.form.SysLoginForm;
import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.configuration.utils.RedisUtils;
import com.maibaduoduo.service.SysCaptchaService;
import com.maibaduoduo.service.SysUserService;
import com.maibaduoduo.service.SysUserTokenService;
import com.maibaduoduo.sys.entity.SysCaptchaEntity;
import com.maibaduoduo.sys.entity.SysUserEntity;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * 用户登录
 */
@RestController
public class SysLoginController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysCaptchaService sysCaptchaService;
	@Autowired
	private RedisUtils redisUtils;

	/**
	 * 验证码
	 * @param response
	 * @param uuid
	 * @throws IOException
	 */
	@GetMapping("captcha.jpg")
	@ApiOperation("验证码")
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 用户登录
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/sys/login")
	@ApiOperation("用户登录")
	public Map<String, Object> login(@RequestBody SysLoginForm form) throws Exception {
		SysCaptchaEntity sysCaptchaEntity = new SysCaptchaEntity();
		JSONObject jsonObject = JSON.parseObject(redisUtils.get(form.getUuid()));
		if (Objects.nonNull(jsonObject)) {
			sysCaptchaEntity.setCode(String.valueOf(jsonObject.get("code")));
			sysCaptchaEntity.setUuid(String.valueOf(jsonObject.get("uuid")));
			boolean captcha = this.validate(sysCaptchaEntity, form.getCaptcha());
			if (!captcha) {
				return R.error("验证码不正确");
			}
		}
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());
		if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return R.error("账号或密码不正确");
		}
		if (user.getStatus() == 0) {
			return R.error("账号已被锁定,请联系管理员");
		}
		R r = sysUserTokenService.createToken(user.getTenantId(), user.getUsername(), user.getUserId());
		return r;
	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	@ApiOperation("用户推出")
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	
}
