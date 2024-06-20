package com.exercise.stream.service.integration.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("remote.services")
public class RemoteServiceProperties {

    private ServiceConfig weather = new ServiceConfig();

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServiceConfig {

        private String url;
        private String userApiKey;
    }
}