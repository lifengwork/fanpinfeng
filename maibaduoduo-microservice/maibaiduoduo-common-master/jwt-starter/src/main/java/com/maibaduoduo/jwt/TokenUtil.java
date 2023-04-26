package com.maibaduoduo.jwt;

import cn.hutool.core.convert.Convert;
import com.maibaduoduo.jwt.model.AuthorizationInfo;
import com.maibaduoduo.jwt.model.JwtUserInfo;
import com.maibaduoduo.jwt.model.Token;
import com.maibaduoduo.jwt.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.maibaduoduo.jwt.common.BaseContextConstants.*;

/**
 * 认证工具类
 *
 */
@AllArgsConstructor
public class TokenUtil {
    /**
     * 认证服务端使用，如 authority-server
     * 生成和 解析token
     */
    private JwtProperties authServerProperties;

    /**
     * 创建认证token
     *
     * @param userInfo 用户信息
     * @return token
     */
    public AuthorizationInfo createAuthInfo(JwtUserInfo userInfo, Long expireMillis) {
		
        if (expireMillis == null || expireMillis <= 0) {
            expireMillis = authServerProperties.getExpire();
        }

        //设置jwt参数
        Map<String, String> param = new HashMap<>(16);
        param.put(JWT_KEY_TOKEN_TYPE, BEARER_HEADER_KEY);
        param.put(JWT_KEY_USER_ID, Convert.toStr(userInfo.getUserId(), "0"));
        param.put(JWT_KEY_MOBILE, userInfo.getMobile());
        param.put(JWT_KEY_NAME, userInfo.getUserName());
        param.put(JWT_KEY_TENANT, userInfo.getTenantId());

        Token token = JwtUtil.createJWT(param, expireMillis);

        AuthorizationInfo authInfo = new AuthorizationInfo();
        authInfo.setUserName(userInfo.getUserName());
        authInfo.setMobile(userInfo.getMobile());
        authInfo.setUserId(userInfo.getUserId());
        authInfo.setTenantId(userInfo.getTenantId());
        authInfo.setTokenType(BEARER_HEADER_KEY);
        authInfo.setToken(token.getToken());
        authInfo.setExpire(token.getExpire());
        authInfo.setExpiration(token.getExpiration());
        authInfo.setRefreshToken(createRefreshToken(userInfo).getToken());
        return authInfo;
    }

    /**
     * 创建refreshToken
     *
     * @param userInfo 用户信息
     * @return refreshToken
     */
    private Token createRefreshToken(JwtUserInfo userInfo) {
        Map<String, String> param = new HashMap<>(16);
        param.put(JWT_KEY_TOKEN_TYPE, REFRESH_TOKEN_KEY);
        param.put(JWT_KEY_USER_ID, Convert.toStr(userInfo.getUserId(), "0"));
        param.put(JWT_KEY_MOBILE, Convert.toStr(userInfo.getMobile(), "0"));
        param.put(JWT_KEY_TENANT, Convert.toStr(userInfo.getTenantId(), "0"));
        param.put(JWT_KEY_NAME, Convert.toStr(userInfo.getUserName(), "0"));
        return JwtUtil.createJWT(param, authServerProperties.getRefreshExpire());
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public AuthorizationInfo parseJWT(String token) {
       return JwtUtil.getAuthorizationInfo(token).setToken(token);
    }
}
