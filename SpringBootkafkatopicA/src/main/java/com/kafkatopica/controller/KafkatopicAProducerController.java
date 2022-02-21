package com.kafkatopica.controller;

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
import com.kafkatopica.model.AddModel;

@RestController
public class KafkatopicAProducerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkatopicAProducerController.class);
	
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	
    @Autowired
    private Gson gson;
    
    
    @PostMapping("/produce")
    public ResponseEntity<String> postModelToKafka(@RequestBody AddModel add)
            throws InterruptedException, ExecutionException {
    	LOGGER.info("Entered into KafkatopicAProducerController::postModelToKafka");
        ListenableFuture<SendResult<String, String>> ack = kafkaTemplate.send("topicA", gson.toJson(add));
        LOGGER.info("Message Produced: {}", gson.toJson(add));
        return new ResponseEntity<>(ack.get().getProducerRecord().value(), HttpStatus.OK);
    }
}
