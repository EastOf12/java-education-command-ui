package com.example.crudapp.repositories.inmemory;

import com.example.crudapp.api.DAO;
import com.example.crudapp.entites.trip.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTripDAO implements DAO<Trip> {
    private final Map<Long, Trip> trips = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public void save(Trip trip) {
        if (trip.getId() == null) {
            trip.setId(idGenerator.getAndIncrement());
        }
        trips.put(trip.getId(), trip);
    }

    @Override
    public Trip findById(Long id) {
        return trips.get(id);
    }

    @Override
    public List<Trip> findAll() {
        return new ArrayList<>(trips.values());
    }

    @Override
    public void update(Trip trip) {
        if (trip.getId() != null && trips.containsKey(trip.getId())) {
            trips.put(trip.getId(), trip);
        }
    }

    @Override
    public void delete(Long id) {
        trips.remove(id);
    }
}