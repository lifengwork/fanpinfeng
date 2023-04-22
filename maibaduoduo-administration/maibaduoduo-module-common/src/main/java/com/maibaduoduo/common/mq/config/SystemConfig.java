package com.maibaduoduo.common.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Component
public class SystemConfig {

    @Value("${mq.retry.count:3}")
    private int mqRetryCount;
    
    public int getMqRetryCount() {
        return mqRetryCount;
    }

    public void setMqRetryCount(int mqRetryCount) {
        this.mqRetryCount = mqRetryCount;
    }
}
