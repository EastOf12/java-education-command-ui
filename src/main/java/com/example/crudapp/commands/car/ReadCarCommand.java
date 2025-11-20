package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.requests.car.CreateCarRequest;
import com.example.crudapp.requests.car.UpdateCarRequest;

import java.util.Scanner;

public class ReadCarCommand implements Command {

    private static ReadCarCommand instance;
    private final Service<Car, CreateCarRequest, UpdateCarRequest> carService;
    private final Scanner scanner;

    private ReadCarCommand(Service<Car, CreateCarRequest, UpdateCarRequest> carService, Scanner scanner) {
        this.carService = carService;
        this.scanner = scanner;
    }

    public static synchronized ReadCarCommand getInstance(
            Service<Car, CreateCarRequest, UpdateCarRequest> carService, Scanner scanner) {
        if (instance == null) {
            instance = new ReadCarCommand(carService, scanner);
        }
        return instance;
    }

    @Override
    public Command execute() {
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

        return CarMenuCommand.getInstance();
    }
}
