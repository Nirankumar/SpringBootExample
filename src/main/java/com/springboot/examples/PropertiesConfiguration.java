package com.springboot.examples;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.properties")
@PropertySource("classpath:common.properties")
public class PropertiesConfiguration {
	
	@PostConstruct
	public void init() {
		log.info("All properties loaded");
	}
}
