package com.currency.Exchange.controller;

import com.currency.Exchange.model.RequestDto;
import com.currency.Exchange.model.RequestResponse;
import com.currency.Exchange.model.RequestResponseRepository;
import com.currency.Exchange.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private RequestResponseRepository requestResponseRepository;

    @PostMapping("/send-request")
    public void sendRequest(@RequestBody RequestDto requestDto) {
        String requestId = requestDto.getRequestId();
        String requestMessage = requestDto.getRequestMessage();

        // save request to db
        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setRequestId(requestId);
        requestResponse.setRequestMessage(requestMessage);
        requestResponseRepository.save(requestResponse);

        // send request to kafka
        kafkaProducerService.sendRequest(requestId, requestMessage);
    }
}

