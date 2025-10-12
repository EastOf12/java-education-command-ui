package com.example.crudapp.services;

import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Service;
import com.example.crudapp.entites.car.Car;

import java.util.List;

public class CarService implements Service<Car> {
    private final DAO<Car> carDAO;

    public CarService(DAO<Car> carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public List<Car> getAll() {
        return carDAO.findAll();
    }

    @Override
    public Car getById(Long id) {
        return carDAO.findById(id);
    }

    @Override
    public void save(Car car) {
        carDAO.save(car);
    }

    @Override
    public void update(Car car) {
        carDAO.update(car);
    }

    @Override
    public void delete(Long id) {
        carDAO.delete(id);
    }
}