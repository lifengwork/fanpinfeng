package com.maibaduoduo.base.other.disruptor.park;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/11/1 0001.
 */
public class ParkEvent implements Serializable{
    private String carlicense;//车牌号

    public String getCarlicense() {
        return carlicense;
    }

    public void setCarlicense(String carlicense) {
        this.carlicense = carlicense;
    }
}
