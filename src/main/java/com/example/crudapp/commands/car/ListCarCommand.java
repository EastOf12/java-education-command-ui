package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;

public class ListCarCommand implements Command {

    private final Service<Car> carService;

    public ListCarCommand(Service<Car> carService) {
        this.carService = carService;
    }

    @Override
    public void execute() {
        System.out.println("=== Список автомобилей ===");
        java.util.List<Car> cars = carService.getAll();
        if (cars.isEmpty()) {
            System.out.println("Нет автомобилей");
        } else {
            cars.forEach(System.out::println);
        }
    }
}
