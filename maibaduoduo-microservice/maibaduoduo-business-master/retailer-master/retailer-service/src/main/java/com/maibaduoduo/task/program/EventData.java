package com.maibaduoduo.task.program;

import java.io.Serializable;

public class EventData implements Serializable {
    private String areaCode;//区域代码
    private String content;

    public String getContent() {
        return content;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public EventData setContent(String content, String areaCode) {
        this.content = content;
        this.areaCode = areaCode;
        return this;
    }
}
