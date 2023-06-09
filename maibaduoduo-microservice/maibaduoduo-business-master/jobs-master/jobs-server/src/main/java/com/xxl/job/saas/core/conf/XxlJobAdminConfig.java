package com.xxl.job.saas.core.conf;

import com.xxl.job.saas.dao.XxlJobGroupDao;
import com.xxl.job.saas.dao.XxlJobInfoDao;
import com.xxl.job.saas.dao.XxlJobLogDao;
import com.xxl.job.saas.dao.XxlJobRegistryDao;
import com.xxl.job.core.biz.AdminBiz;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Configuration
public class XxlJobAdminConfig implements InitializingBean {
    private static XxlJobAdminConfig adminConfig = null;
    @Resource
    public XxlJobLogDao xxlJobLogDao;
    @Resource
    public XxlJobInfoDao xxlJobInfoDao;

    // conf
    @Resource
    public XxlJobRegistryDao xxlJobRegistryDao;
    @Resource
    public XxlJobGroupDao xxlJobGroupDao;
    @Resource
    public AdminBiz adminBiz;
    @Value("${xxl.job.mail.host}")
    private String mailHost;
    @Value("${xxl.job.mail.port}")
    private String mailPort;
    @Value("${xxl.job.mail.ssl}")
    private boolean mailSSL;
    @Value("${xxl.job.mail.username}")
    private String mailUsername;
    @Value("${xxl.job.mail.password}")
    private String mailPassword;
    @Value("${xxl.job.mail.sendNick}")
    private String mailSendNick;
    @Value("${xxl.job.login.username}")
    private String loginUsername;

    // dao, service
    @Value("${xxl.job.login.password}")
    private String loginPassword;
    @Value("${xxl.job.i18n}")
    private String i18n;
    @Value("${xxl.job.accessToken}")
    private String accessToken;

    public static XxlJobAdminConfig getAdminConfig() {
        return adminConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;
    }

    public String getMailHost() {
        return mailHost;
    }

    public String getMailPort() {
        return mailPort;
    }

    public boolean isMailSSL() {
        return mailSSL;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public String getMailSendNick() {
        return mailSendNick;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getI18n() {
        return i18n;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public XxlJobLogDao getXxlJobLogDao() {
        return xxlJobLogDao;
    }

    public XxlJobInfoDao getXxlJobInfoDao() {
        return xxlJobInfoDao;
    }

    public XxlJobRegistryDao getXxlJobRegistryDao() {
        return xxlJobRegistryDao;
    }

    public XxlJobGroupDao getXxlJobGroupDao() {
        return xxlJobGroupDao;
    }

    public AdminBiz getAdminBiz() {
        return adminBiz;
    }

}
