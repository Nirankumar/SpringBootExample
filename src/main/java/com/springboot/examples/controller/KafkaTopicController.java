package com.springboot.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class KafkaTopicController {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@GetMapping("/kafka/send")
	@Operation(summary = "Send Kafka Message")
	public String sendMessage(@RequestParam("message") String msg) {
		try {
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("quickstart-events", msg);
		    future.completable().whenComplete((result, ex) -> {
		        if (ex == null) {
		            log.info("Sent message=[" + msg + 
		                 "] with offset=[" + result.getRecordMetadata().offset() + "]");
		        } else {
		            log.info("Unable to send message=[" + 
		                msg + "] due to : " + ex.getMessage());
		        }
		    });
            return "Sent message=[" + msg + "]";
		} catch (Exception ex) {
			log.warn("Exception occurred while searching IP",ex);
			return "Message sending failed";
		}
	}
}
