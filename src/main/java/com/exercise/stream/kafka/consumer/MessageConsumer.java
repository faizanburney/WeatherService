package com.exercise.stream.kafka.consumer;

import com.exercise.stream.dto.UserDTO;
import com.exercise.stream.service.WeatherService;
import com.exercise.stream.service.integration.WeatherApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final WeatherApiClient weatherApiClient;
    private final WeatherService weatherService;

    @KafkaListener(topics = "${kafka.topics.location-event}", groupId = "${kafka.consumer-groups.location-event}")
    public void listen(UserDTO userDTO) {

        System.out.println("Received message: " + userDTO.toString());
        //validations

        var location = weatherApiClient.getCountryDetailsUsingGeoCords(userDTO.getLatitude(),
                                                                       userDTO.getLongitude()).orElseThrow();

        var weather = weatherApiClient.getWeatherDetailsUsingGeoCords(userDTO.getLatitude(),
                                                                      userDTO.getLongitude()).orElseThrow();

        weatherService.saveUserAndWeatherData(userDTO, location[0], weather);
    }
}