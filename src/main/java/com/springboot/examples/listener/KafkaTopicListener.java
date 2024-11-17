package com.springboot.examples.listener;

import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaTopicListener {
	@KafkaListener(topics = "quickstart-events", groupId = "test-consumer-group")
	public void listenToTopic(String message) {
	    log.info("Received Message in group test-consumer-group: " + message);
	}
}
