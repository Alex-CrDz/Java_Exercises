package com.globant.topic_7.Persistence;

import com.globant.topic_7.Persistence.Model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    Optional<Location> findByLatitudeAndLongitude(double latitude, double longitude);

    boolean existsByLatitudeAndLongitude(double latitude, double longitude);
}
