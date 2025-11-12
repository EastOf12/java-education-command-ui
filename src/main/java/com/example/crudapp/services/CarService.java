package com.example.crudapp.services;

import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Service;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.exception.NotFoundException;
import com.example.crudapp.mappers.CarMapper;
import com.example.crudapp.requests.car.CreateCarRequest;
import com.example.crudapp.requests.car.UpdateCarRequest;

import java.util.List;

public class CarService implements Service<Car, CreateCarRequest, UpdateCarRequest> {
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
    public Car save(CreateCarRequest createCarRequest) {
        Car car = CarMapper.mapToNewCar(createCarRequest);

        if(carDAO.save(car)) {
            return car;
        } else {
            System.out.println("Ошибка при сохранении автомобиля");
            return null;
        }
    }

    @Override
    public Car update(Long id, UpdateCarRequest updateCarRequest) {
        Car updateCar = getById(id);

        if(updateCar == null) {
            throw new NotFoundException("Автомобиль с ID " + id + " не найден.");
        }

        Car car = CarMapper.mapToUpdateCar(updateCar, updateCarRequest);

        if(carDAO.update(car)) {
            return car;
        } else {
            System.out.println("Ошибка при обновлении автомобиля");
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        carDAO.delete(id);
    }
}