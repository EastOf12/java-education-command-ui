package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.requests.car.CreateCarRequest;
import com.example.crudapp.requests.car.UpdateCarRequest;

public class ListCarCommand implements Command {

    private static ListCarCommand instance;
    private final Service<Car, CreateCarRequest, UpdateCarRequest> carService;

    private ListCarCommand(Service<Car, CreateCarRequest, UpdateCarRequest> carService) {
        this.carService = carService;
    }

    public static synchronized ListCarCommand getInstance(Service<Car, CreateCarRequest, UpdateCarRequest> carService) {
        if (instance == null) {
            instance = new ListCarCommand(carService);
        }

        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Список автомобилей ===");
        java.util.List<Car> cars = carService.getAll();
        if (cars.isEmpty()) {
            System.out.println("Нет автомобилей");
        } else {
            cars.forEach(System.out::println);
        }

        return CarMenuCommand.getInstance();
    }
}
