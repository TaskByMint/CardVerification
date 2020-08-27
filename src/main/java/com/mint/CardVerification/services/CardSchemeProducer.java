package com.mint.CardVerification.services;

import com.google.gson.Gson;
import com.mint.CardVerification.dto.PayloadResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardSchemeProducer {

    private static final String TOPIC = "com.ng.vela.even.card_verified";


    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    CardSchemeProducer (KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void sendMessage(PayloadResponse message) {

       // convert the payload object to json
        Gson gson = new Gson();
        String jsonString = gson.toJson(message);

        log.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, jsonString);
    }
}
