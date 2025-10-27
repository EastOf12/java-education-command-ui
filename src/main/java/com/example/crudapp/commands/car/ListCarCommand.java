package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;

public class ListCarCommand implements Command {

    private static ListCarCommand instance;
    private final Service<Car> carService;

    private ListCarCommand(Service<Car> carService) {
        this.carService = carService;
    }

    public static synchronized ListCarCommand getInstance(Service<Car> carService) {
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

        return CarMenuCommand.getInstance().execute();
    }
}
