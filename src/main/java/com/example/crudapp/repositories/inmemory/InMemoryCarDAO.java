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
    public boolean save(Car car) {
        if (car.getId() == null) {
            car.setId(idGenerator.getAndIncrement());
        }
        cars.put(car.getId(), car);

        return true;
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
    public boolean update(Car car) {
        if (car.getId() != null && cars.containsKey(car.getId())) {
            cars.put(car.getId(), car);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(cars.containsKey(id)) {
            cars.remove(id);
            return true;
        }

        return false;
    }
}