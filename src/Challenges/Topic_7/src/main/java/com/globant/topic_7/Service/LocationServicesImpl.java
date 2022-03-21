package com.globant.topic_7.Service;

import com.globant.topic_7.Persistence.LocationRepository;
import com.globant.topic_7.Persistence.Model.Location;
import com.globant.topic_7.Service.Interfaces.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LocationServicesImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepo;

    @Override
    public Location getLocation(long idLocation) {
        AtomicReference<Location> location = new AtomicReference<Location>();
        locationRepo.findById(idLocation)
                .ifPresentOrElse(dbLocation -> location.set(dbLocation), () -> {
                    throw new RuntimeException("Location not found");
                });
        return location.get();
    }

    @Override
    public Location getLocation(double latitude, double longitude) throws RuntimeException {
        AtomicReference<Location> location = new AtomicReference<Location>();
        locationRepo.findByLatitudeAndLongitude(latitude, longitude)
                .ifPresentOrElse(dbLocation -> location.set(dbLocation), () -> {
                    throw new RuntimeException("Location not found");
                });
        return location.get();
    }

    @Override
    public List<Location> getAll() throws Exception {
        List<Location> locationList = new ArrayList<Location>();
        if (locationRepo.count() == 0) {
            throw new Exception("Any location/No records");
        }
        for (Location item : locationRepo.findAll()) {
            locationList.add(item);
        }
        return locationList;
    }

    @Override
    public Location newLocation(Location location) throws Exception {
        if (locationRepo.existsByLatitudeAndLongitude(location.getLatitude(), location.getLongitude())) {
            throw new Exception("Location already exist");
        }
        try {
            location = locationRepo.save(location);
        } catch (Exception e) {
            throw new Exception("Location not saved: " + e.getMessage());
        }
        return location;
    }

    @Override
    public Location editLocation(Location location) throws Exception {
        if (!locationRepo.existsByLatitudeAndLongitude(location.getLatitude(), location.getLongitude())) {
            throw new Exception("Location not found/not exist");
        }
        try {
            location = locationRepo.save(location);
        } catch (Exception e) {
            throw new Exception("Location not updated: " + e.getMessage());
        }
        return location;
    }

    @Override
    public Location deleteLocation(long idLocation) throws Exception {
        if (!locationRepo.existsById(idLocation)) {
            throw new Exception("Location not found/not exist");
        }
        AtomicReference<Location> toDelete = new AtomicReference<Location>();
        locationRepo.findById(idLocation)
                .ifPresent(dbLocation -> {
                    toDelete.set(dbLocation);
                    try {
                        locationRepo.delete(toDelete.get());
                    } catch (Exception e) {
                        throw new RuntimeException("Location not deleted");
                    }
                });
        return toDelete.get();
    }
}
