package com.example.crudapp.repositories.inmemory;

import com.example.crudapp.api.DAO;
import com.example.crudapp.entites.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryCarDAO implements DAO<Car> {
    private final Map<Long, Car> cars = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public void save(Car car) {
        if (car.getId() == null) {
            car.setId(idGenerator.getAndIncrement());
        }
        cars.put(car.getId(), car);
    }

    @Override
    public Car findById(Long id) {
        return cars.get(id);
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public void update(Car car) {
        if (car.getId() != null && cars.containsKey(car.getId())) {
            cars.put(car.getId(), car);
        }
    }

    @Override
    public void delete(Long id) {
        cars.remove(id);
    }
}