package com.example.kafkadriverlocation.service;

import com.example.kafkadriverlocation.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverLocationService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Boolean updateDriverLocation(String location) throws InterruptedException {
        try {
            kafkaTemplate.send(Utility.TOPIC_NAME, location);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
