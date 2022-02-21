package com.kafkatopicb.Controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kafkatopicb.model.AddModel;

@RestController
public class KafkatopicBProducerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkatopicBProducerController.class);
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
    private Gson gson;
	
	@PostMapping("/producetopicB")
    public ResponseEntity<String> postModelToKafka(@RequestBody AddModel emp)
            throws InterruptedException, ExecutionException {
	 try {
		 String a = gson.toJson(emp.getFieldA());
		 String b = gson.toJson(emp.getFieldB());
		 Integer c = Integer.parseInt(a) + Integer.parseInt(b);
		 ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send("topicB", c.toString());
		LOGGER.info("Kafka topicB event produce is : {}", c.toString());
		 return new ResponseEntity<>(result.get().getProducerRecord().value(), HttpStatus.OK);
	 } catch(Exception e) {
		 LOGGER.error("Exception occured in Kafka topicB event producer : {}", e.getMessage());
		 return new ResponseEntity<>("Exception occured in Kafka topicB event producer "+ e, HttpStatus.EXPECTATION_FAILED);
	 }        
    }
}
