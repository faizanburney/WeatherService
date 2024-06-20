package com.exercise.stream;

import com.exercise.stream.dao.UserRepository;
import com.exercise.stream.dao.UserWeatherRepo;
import com.exercise.stream.model.UserEntity;
import com.exercise.stream.model.UserWeatherEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.exercise.stream")
public class UserWeatherIntergrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserWeatherRepo userWeatherRepo;

    @AfterEach
    void cleanup() {

        userRepository.deleteAll();
    }

    @Test
    void getWeatherResponse() throws Exception {

        var user1 = userRepository.save(buildRandomUserEntity("Test1"));
        userWeatherRepo.save(buildRandomUserWeatherEntity(user1.getId()));
        var user2 = userRepository.save(buildRandomUserEntity("Test2"));
        userWeatherRepo.save(buildRandomUserWeatherEntity(user2.getId()));

        MvcResult mvcResult = mockMvc.perform(get("/v1/weather"))
                                     .andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        String expectedResult = "{\"usersWeatherDetails\":[{\"username\":\"Test1\",\"city\":\"berlin\",\"country\":null,\"maxTemp\":11,\"minTemp\":10,\"feels\":null},{\"username\":\"Test2\",\"city\":\"berlin\",\"country\":null,\"maxTemp\":11,\"minTemp\":10,\"feels\":null}]}";
        assertEquals(expectedResult, responseBody);
    }

    @Test
    void getWeatherFailedResponse() throws Exception {

        var user1 = userRepository.save(buildRandomUserEntity("Test1"));

        MvcResult mvcResult = mockMvc.perform(get("/v1/weather"))
                                     .andExpect(status().isBadRequest()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        System.out.println(responseBody);
    }

    private UserEntity buildRandomUserEntity(final String name) {

        return UserEntity.builder()
                         .id(UUID.randomUUID())
                         .name(name)
                         .city("berlin")
                         .build();
    }

    private UserWeatherEntity buildRandomUserWeatherEntity(final UUID userId) {

        return UserWeatherEntity.builder()
                                .id(UUID.randomUUID())
                                .userID(userId)
                                .temperature(6L)
                                .minTemp(10L)
                                .maxTemp(11L)
                                .build();
    }
}
