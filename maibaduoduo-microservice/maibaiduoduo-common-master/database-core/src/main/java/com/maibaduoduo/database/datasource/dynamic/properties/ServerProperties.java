package com.maibaduoduo.database.datasource.dynamic.properties;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @Description: 默认数服务器127.0.0.1
 * @date: 2023/4/17 18:26
 * @Author: pm2022
 */
@Data
public class ServerProperties {
    private List<Server> serverList;

    public List<Server> getServerList() {
        if (Objects.isNull(serverList) || serverList.isEmpty()) {
            serverList = Lists.newArrayList(new Server().genSerPro("127.0.0.1", "3306"), new Server().genSerPro("localhost", "3306"));
        }
        return serverList;
    }

    public void setServerList(List<Server> serverList) {
        this.serverList = serverList;
    }

   public static class Server {
        private String host;
        private String port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public Server genSerPro(String host, String port) {
            this.host = host;
            this.port = port;
            return this;
        }
    }
}

