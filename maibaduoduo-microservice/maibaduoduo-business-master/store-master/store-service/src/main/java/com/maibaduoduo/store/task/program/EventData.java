package com.maibaduoduo.store.task.program;

import java.io.Serializable;

public class EventData implements Serializable {
    private String content;

    public String getContent() {
        return content;
    }

    public EventData setContent(String content) {
        this.content = content;
        return this;
    }
}
