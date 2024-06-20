package com.exercise.stream.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity(name = "user_weather")
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWeatherEntity {

    @Id
    private UUID id;
    private UUID userID;//index
    private Long temperature;
    private Long feels;
    private Long minTemp;
    private Long maxTemp;
}
