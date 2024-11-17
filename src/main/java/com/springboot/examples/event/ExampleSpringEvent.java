package com.springboot.examples.event;

import org.springframework.context.ApplicationEvent;

public class ExampleSpringEvent extends ApplicationEvent {
	
	private String message;
	
	public ExampleSpringEvent(Object aSource, String aMessage) {
		super(aSource);
		this.message = aMessage;
	}
	
	public String getMessage() {
        return this.message;
    }
}
