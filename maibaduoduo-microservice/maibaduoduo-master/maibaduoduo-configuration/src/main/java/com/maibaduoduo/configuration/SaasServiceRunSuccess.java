package com.maibaduoduo.configuration;

import com.maibaduoduo.configuration.props.ConsoleLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SaasServiceRunSuccess implements ApplicationListener<AvailabilityChangeEvent> {
    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(AvailabilityChangeEvent availabilityChangeEvent) {
        if(ReadinessState.ACCEPTING_TRAFFIC==availabilityChangeEvent.getState()){
            ConsoleLog.out(environment);
        }
    }
}
