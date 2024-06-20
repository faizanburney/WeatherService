package com.exercise.stream.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserWeatherDTO {

    private String username;
    private String city;
    private String country;
    private Long maxTemp;
    private Long minTemp;
    private Long feels;
}
