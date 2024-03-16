package com.example.kafkauserlocation.service;

import com.example.kafkauserlocation.service.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ListenLocationService {

    @KafkaListener(topics = Utility.TOPIC_NAME, groupId = "user-group")
    public void listenDriverLocation(String location) {
        log.info("Listening location: {}", location);
    }
}
