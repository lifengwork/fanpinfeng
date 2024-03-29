/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.event;

import lombok.Data;

import java.io.Serializable;

/**
 * ProgramEvent.
 *
 * @author saas
 */
@Data
public class ProgramEvent implements Serializable {

    private ProgramTask programTask;
    private int type;

    /**
     * help gc.
     */
    public void clear() {
        programTask = null;
    }
}
