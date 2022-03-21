package com.globant.topic_7.Service.Interfaces;

import com.globant.topic_7.Persistence.Model.Location;

import java.util.List;

public interface LocationService {
    Location getLocation(long idLocation);

    Location getLocation(double latitude, double longitude);

    List<Location> getAll() throws Exception;

    Location newLocation(Location location) throws Exception;

    Location editLocation(Location location) throws Exception;

    Location deleteLocation(long idLocation) throws Exception;
}
