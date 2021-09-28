package com.edu.upc.ilanguagesession.config;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

   // @Autowired
    //private AxonConfiguration axonConfiguration;
    @Autowired
    private EventBus eventBus;

    @Autowired
    public void configure(@Qualifier( "localSegment") SimpleCommandBus simpleCommandBus) {
        simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
    }
}
