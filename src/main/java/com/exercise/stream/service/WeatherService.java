package com.exercise.stream.service;

import com.exercise.stream.builder.UserWeatherDTOBuilder;
import com.exercise.stream.dao.UserRepository;
import com.exercise.stream.dao.UserWeatherRepo;
import com.exercise.stream.dto.UserDTO;
import com.exercise.stream.dto.UserResponse;
import com.exercise.stream.dto.UserWeatherDTO;
import com.exercise.stream.exception.ApiValidationException;
import com.exercise.stream.model.UserEntity;
import com.exercise.stream.model.UserWeatherEntity;
import com.exercise.stream.service.integration.dto.WeatherResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final UserRepository userRepository;

    private final UserWeatherRepo userWeatherRepo;

    public UserResponse getUserWeatherDetails() {

        List<UserWeatherDTO> userWeatherResponse = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserWeatherEntity> userWeatherDTOS = userWeatherRepo.
                findAllByUserIDIn(userEntities.stream().map(UserEntity::getId).toList());
        Map<UUID, UserWeatherEntity> userIDtoUserWeatherMap = generateUserIDtoUserWeatherMap(userWeatherDTOS);
        userEntities.forEach(userEntity -> {
            if (!userIDtoUserWeatherMap.containsKey(userEntity.getId())) {
                throw new ApiValidationException("internal server error");
            }
            var userWeather = userIDtoUserWeatherMap.get(userEntity.getId());
            userWeatherResponse.add(UserWeatherDTOBuilder.build(userEntity, userWeather));
        });
        return UserResponse.builder().usersWeatherDetails(userWeatherResponse).build();

    }

    private Map<UUID, UserWeatherEntity> generateUserIDtoUserWeatherMap(final List<UserWeatherEntity> userWeatherDTOS) {

        return userWeatherDTOS.stream().collect(Collectors.toMap(UserWeatherEntity::getUserID,
                                                                 Function.identity()));
    }

    @Transactional(rollbackOn = Exception.class)
    public void saveUserAndWeatherData(final UserDTO userDTO,
                                       final WeatherResponse.Location location,
                                       final WeatherResponse.Weather weather) {

        if (userRepository.findByName(userDTO.getUsername()).isEmpty()) {
            UserEntity newUser = UserEntity.builder().id(UUID.randomUUID())
                                           .name(userDTO.getUsername())
                                           .country(location.getCountry())
                                           .city(location.getCountry()).build();

            UserEntity savedUser = userRepository.save(newUser);

            UserWeatherEntity userWeatherEntity = UserWeatherEntity.builder().id(UUID.randomUUID()).
                                                                   userID(savedUser.getId()).
                                                                   temperature(weather.getTemp())
                                                                   .maxTemp(weather.getMax_temp())
                                                                   .minTemp(weather.getMin_temp())
                                                                   .feels(weather.getFeels_like())
                                                                   .build();
            userWeatherRepo.save(userWeatherEntity);
        }
    }
}
