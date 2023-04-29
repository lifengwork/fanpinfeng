/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.jwt.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.AES;
import com.maibaduoduo.jwt.common.BaseContextConstants;
import com.maibaduoduo.jwt.common.BizException;
import com.maibaduoduo.jwt.common.code.ExceptionCode;
import com.maibaduoduo.jwt.model.AuthorizationInfo;
import com.maibaduoduo.jwt.model.Token;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static com.maibaduoduo.jwt.common.BaseContextConstants.*;
import static com.maibaduoduo.jwt.common.code.ExceptionCode.JWT_PARSER_TOKEN_FAIL;
/**
 * Secure工具类
 */
@Slf4j
public class JwtUtil {

    private static byte[] BASE64_SECURITY_KEY = Base64.getEncoder().encode(BaseContextConstants.JWT_SIGN_KEY.getBytes(Charsets.UTF_8));
    private static final RsaKeyProperties rsaKeyProperties = (RsaKeyProperties)SpringContextUtils.getBean("rsaKeyProperties");

    /**
     * 创建令牌
     *
     * @param user   user
     * @param expire 过期时间（秒)
     * @return jwt
     */
    public static Token createJWT(Map<String, String> user,long expire) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS512;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //添加构成JWT的类
        JwtBuilder builder = Jwts.builder()
                .setId(createJTI())
                .setHeaderParam("typ", "JWT")
                .signWith(signatureAlgorithm, rsaKeyProperties.getPrivateKey());
        //设置JWT参数
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user.get(JWT_KEY_TOKEN_TYPE)).append(StrPool.PIPE)
                     .append(user.get(JWT_KEY_USER_ID)).append(StrPool.PIPE)
                     .append(user.get(JWT_KEY_MOBILE)).append(StrPool.PIPE)
                     .append(user.get(JWT_KEY_NAME)).append(StrPool.PIPE)
                     .append(user.get(JWT_KEY_TENANT));
        builder.setSubject(new AES(BASE64_SECURITY_KEY).encryptBase64(stringBuilder.toString()));
        long expMillis = nowMillis + expire * 1000;
        Date exp = new Date(expMillis);
        builder
                .setIssuedAt(new Date())//token创建时间
                .setExpiration(exp)//token的失效时间
                .setNotBefore(now);//设置在多少秒内token无法使用。
        Token tokenInfo = new Token();
        tokenInfo.setToken(builder.compact());
        tokenInfo.setExpire(expire);
        tokenInfo.setExpiration(exp);
        return tokenInfo;
    }

    /**
     * 解析并判断过期|签名错误|token 为空
     * @param jsonWebToken
     * @return
     */
    private static Claims parseJWT(String jsonWebToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(rsaKeyProperties.getPublicKey())
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (ExpiredJwtException ex) {
            throw new BizException(ExceptionCode.JWT_TOKEN_EXPIRED.getCode(), ExceptionCode.JWT_TOKEN_EXPIRED.getMsg());
        } catch (SignatureException ex) {
            throw new BizException(ExceptionCode.JWT_SIGNATURE.getCode(), ExceptionCode.JWT_SIGNATURE.getMsg());
        } catch (IllegalArgumentException ex) {
            throw new BizException(ExceptionCode.JWT_ILLEGAL_ARGUMENT.getCode(), ExceptionCode.JWT_ILLEGAL_ARGUMENT.getMsg());
        } catch (Exception e) {
            log.error("errcode:{}, message:{}", JWT_PARSER_TOKEN_FAIL.getCode(), e.getMessage());
            throw new BizException(JWT_PARSER_TOKEN_FAIL.getCode(), JWT_PARSER_TOKEN_FAIL.getMsg());
        }
    }

    private static Claims getClaims(String token) {
        if (token == null) {
            throw BizException.wrap(JWT_PARSER_TOKEN_FAIL);
        }
        if (token.startsWith(BaseContextConstants.BEARER_HEADER_PREFIX)) {
            String headStr = StrUtil.subAfter(token, BaseContextConstants.BEARER_HEADER_PREFIX, false);
            return parseJWT(headStr);
        }
        throw BizException.wrap(JWT_PARSER_TOKEN_FAIL);
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 解析用户认证信息
     * @param token
     * @return
     */
    public static AuthorizationInfo getAuthorizationInfo(String token){
        Claims claims = getClaims(token);
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        String claim = new AES(BASE64_SECURITY_KEY)
                .decryptStr(claims.getSubject(), Charsets.UTF_8);
        authorizationInfo.setExpiration(claims.getExpiration());
        authorizationInfo.setExpire(claims.getExpiration() != null ? claims.getExpiration().getTime() : 0L);
        String[] claimArr = claim.split(StrPool.PIPEV);
        authorizationInfo.setTokenType(claimArr[0]);
        authorizationInfo.setUserId(Long.valueOf(claimArr[1]));
        authorizationInfo.setMobile(claimArr[2]);
        authorizationInfo.setUserName(claimArr[3]);
        authorizationInfo.setTenantId(claimArr[4]);
        return authorizationInfo;
    }

}
