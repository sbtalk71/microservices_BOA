package com.demo.spring;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

	@KafkaListener(topics = {"demo-topic","demo-topic2"},groupId = "g1")
	public void consumeMessages(String message) {
		System.out.println(message);
	}
}
