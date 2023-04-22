package com.maibaduoduo.common.shiro;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.maibaduoduo.common.service.SystemService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BCryptCredentialsMatcher extends SimpleCredentialsMatcher {
    @Autowired
    private SystemService systemService;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return systemService.validatePassword(String.valueOf((char[])getCredentials(token)),String.valueOf(getCredentials(info)));
    }
}
