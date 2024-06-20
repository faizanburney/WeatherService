package com.exercise.stream.controller;

import com.exercise.stream.dto.UserDTO;
import com.exercise.stream.kafka.producer.LocationStreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationStreamService locationStreamService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<UserDTO> sendMessageToKafkaTopic(@RequestBody UserDTO user) {

        // validation of data will be done here but for now leaving that part for saving time.

        return ResponseEntity.ok().body(locationStreamService.sendUserLocations(user));
    }
}
