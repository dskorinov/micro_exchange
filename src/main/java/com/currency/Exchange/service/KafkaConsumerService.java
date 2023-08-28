package com.currency.Exchange.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final String RESPONSE_TOPIC = "response-topic";

    @KafkaListener(topics = RESPONSE_TOPIC)
    public void receiveResponse(String response) {
        // TODO: response logic
        System.out.println("Received response: " + response);
    }
}
