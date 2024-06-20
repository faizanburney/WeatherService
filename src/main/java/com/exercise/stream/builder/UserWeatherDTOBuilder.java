package com.exercise.stream.builder;

import com.exercise.stream.dto.UserWeatherDTO;
import com.exercise.stream.model.UserEntity;
import com.exercise.stream.model.UserWeatherEntity;

public class UserWeatherDTOBuilder {

    private UserWeatherDTOBuilder() {}

    public static UserWeatherDTO build(final UserEntity userEntity, final UserWeatherEntity userWeatherEntity) {

        return UserWeatherDTO.builder().username(userEntity.getName())
                             .country(userEntity.getCountry())
                             .city(userEntity.getCity())
                             .minTemp(userWeatherEntity.getMinTemp())
                             .maxTemp(userWeatherEntity.getMaxTemp())
                             .feels(userWeatherEntity.getFeels())
                             .build();

    }
}
