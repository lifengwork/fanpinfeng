/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.factory;

import com.lmax.disruptor.EventFactory;
import com.maibaduoduo.store.task.event.ProgramEvent;
import com.maibaduoduo.store.task.event.PurchaseProgramEvent;

/**
 * ProgramEventFactory.
 *
 * @author saas
 */
public class PurchaseProgramEventFactory implements EventFactory<PurchaseProgramEvent> {

    @Override
    public PurchaseProgramEvent newInstance() {
        return new PurchaseProgramEvent();
    }
}
