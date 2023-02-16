package com.demo.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Emp;

@RestController
public class KafkaProducerController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@GetMapping(path="/send/{message}", produces = "text/plain")
	public ResponseEntity<String> sendMessage(@PathVariable("message") String message){
		kafkaTemplate.send("demo-topic", message);
		return ResponseEntity.ok("Message Sent");
	}
	
	//---------JSON SENDER----//
	
	@Autowired
	@Qualifier("jsonKafkaTemplate")
	KafkaTemplate<String, Emp> jsonKafkaTemplate;
	
	@PostMapping(path="/sendjson", produces = "text/plain", consumes = "application/json")
	public ResponseEntity<String> sendJsonMessage(@RequestBody Emp e){
		jsonKafkaTemplate.send("demo-topic", e);
		return ResponseEntity.ok("JSON Message Sent");
	}
	
}
