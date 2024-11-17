package com.springboot.examples.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExampleEventListener {
	
	@EventListener
	public void listenEvents(ExampleSpringEvent aEvent) {
		log.info("Received message: {}",aEvent.getMessage());
	}
}
