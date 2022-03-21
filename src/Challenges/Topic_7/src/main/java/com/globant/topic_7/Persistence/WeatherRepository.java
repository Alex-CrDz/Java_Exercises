package com.globant.topic_7.Persistence;

import com.globant.topic_7.Persistence.Model.WeatherRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherRecord, Long> {
    @Query(value = "select top 1 * " +
            "from weather_records " +
            "order by insert_date desc", nativeQuery = true)
    Optional<WeatherRecord> findLast();

    boolean existsByInsertDate(LocalDateTime timestamp);
}
