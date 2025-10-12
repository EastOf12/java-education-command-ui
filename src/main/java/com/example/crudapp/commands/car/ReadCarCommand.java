package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;

import java.util.Scanner;

public class ReadCarCommand implements Command {

    private final Service<Car> carService;
    private final Scanner scanner;

    public ReadCarCommand(Service<Car> carService, Scanner scanner) {
        this.carService = carService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("=== Просмотр автомобиля ===");
        System.out.print("Введите ID автомобиля: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Car car = carService.getById(id);
            if (car != null) {
                System.out.println(car);
            } else {
                System.out.println("Автомобиль не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }
    }
}
