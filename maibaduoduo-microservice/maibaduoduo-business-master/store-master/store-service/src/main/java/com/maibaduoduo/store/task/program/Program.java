/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.program;

/**
 * 订单加急处理任务
 * @Auth saas
 * @Date 2023-5-7
 */
public interface Program {
    boolean execute(ExecuteObject executeObject);
}
