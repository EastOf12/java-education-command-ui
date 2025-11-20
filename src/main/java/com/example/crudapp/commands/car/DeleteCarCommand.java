package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.commands.Confirm;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.requests.car.CreateCarRequest;
import com.example.crudapp.requests.car.UpdateCarRequest;

import java.util.Scanner;

public class DeleteCarCommand implements Command {
    private static DeleteCarCommand instance;
    private final Service<Car, CreateCarRequest, UpdateCarRequest> carService;
    private final Scanner scanner;

    private DeleteCarCommand(Service<Car, CreateCarRequest, UpdateCarRequest> carService, Scanner scanner) {
        this.carService = carService;
        this.scanner = scanner;
    }

    public static synchronized DeleteCarCommand getInstance(Service<Car, CreateCarRequest, UpdateCarRequest> carService, Scanner scanner) {
        if (instance == null) {
            instance = new DeleteCarCommand(carService, scanner);
        }

        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Удаление автомобиля ===");
        System.out.print("Введите ID автомобиля: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Car car = carService.getById(id);
            if (car != null) {
                System.out.println("Удаление автомобиля: " + car);
                System.out.print("Подтвердите удаление (y/n): ");
                String confirm = scanner.nextLine();
                if (Confirm.valueOf(confirm).equals(Confirm.y)) {
                    if(carService.delete(id)) {
                        System.out.println("Автомобиль удален");
                    } else {
                        System.out.println("Ошибка при удалении автомобиля");
                    }
                } else {
                    System.out.println("Удаление отменено");
                }
            } else {
                System.out.println("Автомобиль не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }

        return CarMenuCommand.getInstance();
    }
}
