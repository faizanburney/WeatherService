package com.exercise.stream.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("kafka.topics")
public class KafkaTopicProperties {

    private String LocationEvent;
}