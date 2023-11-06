/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.task.factory;

import com.lmax.disruptor.EventFactory;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;

/**
 * ProgramEventFactory.
 *
 * @author saas
 */
public class ProgramEventFactory implements EventFactory<ProgramEvent> {

    @Override
    public ProgramEvent newInstance() {
        return new ProgramEvent();
    }
}
