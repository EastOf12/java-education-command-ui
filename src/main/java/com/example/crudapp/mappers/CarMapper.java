package com.example.crudapp.mappers;

import com.example.crudapp.entites.car.Car;
import com.example.crudapp.requests.car.CreateCarRequest;
import com.example.crudapp.requests.car.UpdateCarRequest;

public class CarMapper {
    public static Car mapToNewCar(CreateCarRequest createCarRequest) {
        return new Car(
            createCarRequest.getNumber(),
            createCarRequest.getMaxSeat(),
            createCarRequest.getColor(),
            createCarRequest.getBrand(),
            createCarRequest.getUserId()
        );
    }

    public static Car mapToUpdateCar(Car car, UpdateCarRequest updateCarRequest) {
        if(updateCarRequest.getNumber() != null) {
            car.setNumber(updateCarRequest.getNumber());
        }

        if(updateCarRequest.getMaxSeat() != null) {
            car.setMaxSeat(updateCarRequest.getMaxSeat());
        }

        if(updateCarRequest.getColor() != null) {
            car.setColor(updateCarRequest.getColor());
        }

        if(updateCarRequest.getBrand() != null) {
            car.setBrand(updateCarRequest.getBrand());
        }

        return car;
    }
}
