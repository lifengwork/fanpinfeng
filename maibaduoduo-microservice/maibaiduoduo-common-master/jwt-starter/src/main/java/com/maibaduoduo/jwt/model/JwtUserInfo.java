package com.maibaduoduo.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * jwt 存储的 内容
 *
 */
@Data
@Getter
public class JwtUserInfo implements Serializable {
    private Long userId;
    private String mobile;
    private String userName;
    private String tenantId;
    public JwtUserInfo setValue(Long userId,String mobile,String userName,String tenantId){
        this.userId =userId;
        this.mobile = mobile;
        this.userName = userName;
        this.tenantId = tenantId;
        return this;
    }
}
