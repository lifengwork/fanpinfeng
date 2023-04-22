package com.maibaduoduo.base.other.queue;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/10/30 0030.
 */
public class Msg implements Serializable{
    private int id;
    private String body; // 消息内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Msg(int id, String body) {
        this.id = id;
        this.body = body;
    }
}
