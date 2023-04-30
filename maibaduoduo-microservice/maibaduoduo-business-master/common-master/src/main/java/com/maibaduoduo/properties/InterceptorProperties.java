package com.maibaduoduo.properties;

import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "maibaduoduo.interceptor.path")
public class InterceptorProperties {
    public static List<String> paths = Lists.newArrayList();
    public static String[] excludePaths;
    private String path;
    private boolean open = true;
    private boolean isRegis=true;
    private String excludePath;

    public void setPath(String path) {
        if(open){
            paths.add(path);
        }
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isRegis() {
        return isRegis;
    }

    public void setRegis(boolean regis) {
        isRegis = regis;
    }

    public void setExcludePath(String excludePath) {
        this.excludePath = excludePath;
        excludePaths = excludePath.split(",");
    }
}
