package com.maibaduoduo.jwt.model;

import com.maibaduoduo.jwt.utils.StrPool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Optional;

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
        this.userId = Optional.ofNullable(userId).orElse(0L);
        this.mobile = StringUtils.isEmpty(mobile)?"0":mobile;
        this.userName = StringUtils.isEmpty(userName)?"0":userName;
        this.tenantId = StringUtils.isEmpty(tenantId)?"0":tenantId;
        return this;
    }
}
