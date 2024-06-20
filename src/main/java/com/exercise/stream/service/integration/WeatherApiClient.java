package com.exercise.stream.service.integration;

import com.exercise.stream.service.integration.dto.WeatherResponse;
import com.exercise.stream.service.integration.util.RemoteServiceProperties;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WeatherApiClient {

    private static final String API_USER_KEY = "X-Api-Key";
    private final RestTemplate restTemplate;
    private final RemoteServiceProperties remoteServiceProperties;

    public Optional<WeatherResponse.Location[]> getCountryDetailsUsingGeoCords(final String latitude, final String longitude) {

        final Map<String, String> urlVariables = Map.of("lat", latitude, "lon", longitude);
        val weatherServiceConfig = remoteServiceProperties.getWeather();

        return Optional.ofNullable(restTemplate.exchange(weatherServiceConfig.getUrl() + "/reversegeocoding?lat={lat}&lon={lon}",
                                                         HttpMethod.GET,
                                                         new HttpEntity<>(apiHeaders()),
                                                         WeatherResponse.Location[].class,
                                                         urlVariables)
                                               .getBody());
    }

    public Optional<WeatherResponse.Weather> getWeatherDetailsUsingGeoCords(final String latitude, final String longitude) {

        final Map<String, String> urlVariables = Map.of("lat", latitude, "lon", longitude);
        val weatherServiceConfig = remoteServiceProperties.getWeather();

        return Optional.ofNullable(restTemplate.exchange(weatherServiceConfig.getUrl() + "/weather?lat={lat}&lon={lon}",
                                                         HttpMethod.GET,
                                                         new HttpEntity<>(apiHeaders()),
                                                         WeatherResponse.Weather.class,
                                                         urlVariables)
                                               .getBody());
    }

    private HttpHeaders apiHeaders() {

        val httpHeaders = new HttpHeaders();
        httpHeaders.set(API_USER_KEY, remoteServiceProperties.getWeather().getUserApiKey());
        return httpHeaders;
    }

}
