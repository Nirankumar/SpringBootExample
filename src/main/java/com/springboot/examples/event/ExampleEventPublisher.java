package com.springboot.examples.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExampleEventPublisher {
	
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	
	public void publishEvent(final String message) {
        log.info("Publishing custom event. ");
        ExampleSpringEvent customSpringEvent = new ExampleSpringEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
