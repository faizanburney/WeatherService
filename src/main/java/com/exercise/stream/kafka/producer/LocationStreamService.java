package com.exercise.stream.kafka.producer;

import com.exercise.stream.dto.UserDTO;
import com.exercise.stream.kafka.KafkaTopicProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationStreamService {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public UserDTO sendUserLocations(UserDTO userDTO) {

        this.kafkaTemplate.send(kafkaTopicProperties.getLocationEvent(), userDTO);
        return userDTO;
    }

}


