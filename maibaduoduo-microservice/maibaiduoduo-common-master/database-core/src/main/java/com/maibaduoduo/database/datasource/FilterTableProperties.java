/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.database.datasource;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @Description: //TODO
 * @date: 2023/4/19 17:15
 * @Author: pm2022
 */
@Data
public class FilterTableProperties {
    private List<String> serverList;

    public List<String> getServerList() {
        if (Objects.isNull(serverList) || serverList.isEmpty()) {
            serverList = Lists.newArrayList("");
        }
        return serverList;
    }

    public void setServerList(List<String> serverList) {
        this.serverList = serverList;
    }
}
