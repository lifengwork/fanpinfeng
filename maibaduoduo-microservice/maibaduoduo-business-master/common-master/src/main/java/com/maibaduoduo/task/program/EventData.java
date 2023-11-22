package com.maibaduoduo.task.program;

import java.io.Serializable;

public class EventData implements Serializable {
    private String content;
    //附加值仓库ID
    private String storeId;
    public String getContent() {
        return content;
    }

    public EventData setContent(String content) {
        this.content = content;
        return this;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
