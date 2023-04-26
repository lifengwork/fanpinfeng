package com.maibaduoduo.jwt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * AuthorizationInfo
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "认证信息")
public class AuthorizationInfo {
    @ApiModelProperty(value = "令牌")
    private String token;
    @ApiModelProperty(value = "令牌类型")
    private String tokenType;
    @ApiModelProperty(value = "租户编码")
    private String tenantId;
    @ApiModelProperty(value = "刷新令牌")
    private String refreshToken;
    @ApiModelProperty(value = "登录账号")
    private String userName;
    @ApiModelProperty(value = "用户手机号")
    private String mobile;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "过期时间（秒）")
    private long expire=0L;
    @ApiModelProperty(value = "到期时间")
    private Date expiration;
}
