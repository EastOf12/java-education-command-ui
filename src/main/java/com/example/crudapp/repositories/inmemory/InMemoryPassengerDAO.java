package com.example.crudapp.repositories.inmemory;

import com.example.crudapp.api.DAO;
import com.example.crudapp.entites.passanger.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryPassengerDAO implements DAO<Passenger> {
    private final Map<Long, Passenger> passengers = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public boolean save(Passenger passenger) {
        if (passenger.getId() == null) {
            passenger.setId(idGenerator.getAndIncrement());
        }
        passengers.put(passenger.getId(), passenger);

        return true;
    }

    @Override
    public Passenger findById(Long id) {
        return passengers.get(id);
    }

    @Override
    public List<Passenger> findAll() {
        return new ArrayList<>(passengers.values());
    }

    @Override
    public boolean update(Passenger passenger) {
        if (passenger.getId() != null && passengers.containsKey(passenger.getId())) {
            passengers.put(passenger.getId(), passenger);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(passengers.containsKey(id)) {
            passengers.remove(id);
            return true;
        }

        return false;
    }
}
