package com.currency.Exchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final String TOPIC = "request-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendRequest(String requestId, String requestMessage) {
        String jsonMessage = "{\"requestId\":\"" + requestId + "\",\"message\":\"" + requestMessage + "\"}";
        kafkaTemplate.send(TOPIC, jsonMessage);
    }
}

