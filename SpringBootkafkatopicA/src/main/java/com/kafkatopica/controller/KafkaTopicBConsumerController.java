package com.kafkatopica.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTopicBConsumerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicBConsumerController.class);
	
	@KafkaListener(topics = { "topicB" })
	public void getTopics(@RequestBody String values) {
		LOGGER.info("Kafka topicB event consumed is : {}", values);
		// AddModel model = gson.fromJson(values, AddModel.class);
		//System.out.println("value: " + values);
	}
}
