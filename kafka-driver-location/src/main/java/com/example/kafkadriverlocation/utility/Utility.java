package com.example.kafkadriverlocation.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ObjectUtils;

public class Utility {
    public static final String TOPIC_NAME = "topic-driver-locations";

    public static String objectToJsonString(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            return "null";
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            return "null";
        }
    }
}
