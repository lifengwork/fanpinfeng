package com.maibaduoduo.common.mq.sender;

import com.maibaduoduo.common.mq.config.CustomCorrelationData;
import com.maibaduoduo.common.utils.IdGen;

public interface Sender {
    default CustomCorrelationData correlationData(Object message) {
        return new CustomCorrelationData(IdGen.SnowFlake(), message);
    }
}
