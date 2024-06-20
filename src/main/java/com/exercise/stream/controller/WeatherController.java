package com.exercise.stream.controller;

import com.exercise.stream.dto.UserResponse;
import com.exercise.stream.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<UserResponse> getAllUserWeahterData() {

        // validation of data will be done here but for now leaving that part for saving time.

        return ResponseEntity.ok().body(weatherService.getUserWeatherDetails());
    }
}
