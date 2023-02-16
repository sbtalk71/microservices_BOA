package com.demo.spring;

import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@SpringBootApplication
@EnableKafka
public class SpringbootKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaProducerApplication.class, args);
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		HashMap<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerCOntainer() {
		ConcurrentKafkaListenerContainerFactory<String, String> lc = new ConcurrentKafkaListenerContainerFactory<String, String>();
		lc.setConsumerFactory(consumerFactory());
		return lc;
	}
}
