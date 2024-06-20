package com.exercise.stream.dao;

import com.exercise.stream.model.UserWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserWeatherRepo extends JpaRepository<UserWeatherEntity, UUID> {

    List<UserWeatherEntity> findAllByUserIDIn(final List<UUID> userIds);
}
