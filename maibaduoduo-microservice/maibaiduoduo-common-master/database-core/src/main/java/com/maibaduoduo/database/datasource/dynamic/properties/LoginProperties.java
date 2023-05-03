package com.maibaduoduo.database.datasource.dynamic.properties;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: //TODO
 * @date: 2023/4/17 17:30
 * @Author: pm2022
 */
@NoArgsConstructor
@ConfigurationProperties(prefix = "maibaduoduo.login")
public class LoginProperties {
    private String pcTokenName="token";
    private String pcUrI="/sys/login";
    private String appTokenName="token";
    private String appUri="/app/login";
    private List<String> filterInvalidToken= Lists.newArrayList();
    private String dbCachePrefix="TENANT_DB_URL_*";
    public String getDbCachePrefix() {
        return dbCachePrefix;
    }

    public void setDbCachePrefix(String dbCachePrefix) {
        this.dbCachePrefix = dbCachePrefix;
    }

    public String getPcTokenName() {
        return pcTokenName;
    }

    public void setPcTokenName(String pcTokenName) {
        this.pcTokenName = pcTokenName;
    }

    public String getPcUrI() {
        return pcUrI;
    }

    public void setPcUrI(String pcUrI) {
        this.pcUrI = pcUrI;
    }

    public String getAppTokenName() {
        return appTokenName;
    }

    public void setAppTokenName(String appTokenName) {
        this.appTokenName = appTokenName;
    }

    public String getAppUri() {
        return appUri;
    }

    public void setAppUri(String appUri) {
        this.appUri = appUri;
    }

    public List<String> getFilterInvalidToken() {
        return filterInvalidToken;
    }

    public void setFilterInvalidToken(List<String> filterInvalidToken) {
        this.filterInvalidToken = filterInvalidToken;
    }
}
