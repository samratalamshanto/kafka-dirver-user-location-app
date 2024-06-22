package com.example.kafkadriverlocation.controller;

import com.example.kafkadriverlocation.service.DriverLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest/driver-location")
@RequiredArgsConstructor
@Slf4j
public class DriverLocationController {
    private final DriverLocationService driverLocationService;

    @PutMapping
    public ResponseEntity updateDriverLocation() throws InterruptedException {
        int range = 10;
        while (range > 0) {
            String location = Math.random() + "," + Math.random(); //location comes as api params
            driverLocationService.updateDriverLocation(location);
            log.info("Sending location: {}", location);
            Thread.sleep(1000); //1 sec
            range--;
        }

        return new ResponseEntity<>(Map.of("status", "Location updated successfully"),
                HttpStatus.OK);
    }

    @GetMapping
    public String hello() {
        return "hello";
    }
}
