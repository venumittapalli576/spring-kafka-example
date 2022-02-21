package com.kafkatopicb.Controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.kafkatopicb.model.AddModel;

@RestController
public class KafkaTopicAConsumerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicAConsumerController.class);
	
    @Autowired
    private Gson gson;  
    
    @Autowired
    RestTemplate restTemplate;
    
    @KafkaListener(topics = { "topicA" })
    public void getTopics(@RequestBody String values) {
    	LOGGER.info("Kafka topicA event consumed is : {}", values);
    	try {
    		AddModel model = gson.fromJson(values, AddModel.class);
            LOGGER.info("Model converted value : {}", model.toString());
            
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<AddModel> entity = new HttpEntity<AddModel>(model,headers);
            restTemplate.exchange("http://localhost:8081/producetopicB", HttpMethod.POST, entity, String.class).getBody();
    	} catch (Exception e) {
    		LOGGER.error("Exception occured while consuming KafkaTopicAConsumerController : {}", e.getMessage());
    	}
    }
    
    
}
