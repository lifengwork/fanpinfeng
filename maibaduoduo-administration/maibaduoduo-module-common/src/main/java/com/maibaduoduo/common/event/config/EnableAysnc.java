package com.maibaduoduo.common.event.config;

/**
 * 是否开启异步事件
 */
public class EnableAysnc {
    private boolean enable = false;

    public EnableAysnc(boolean enable){
        this.enable  = enable;
    }
    public boolean isEnable() {
        return enable;
    }
}
