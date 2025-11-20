package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.commands.MainMenuCommand;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.requests.car.CreateCarRequest;
import com.example.crudapp.requests.car.UpdateCarRequest;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;
import com.example.crudapp.services.ServiceInjector;
import com.example.crudapp.services.ServiceKey;

import java.util.Scanner;

public class CarMenuCommand implements Command {
    private static CarMenuCommand instance;
    private final Service<Car, CreateCarRequest, UpdateCarRequest> carService;
    private final Service<User, CreateUserRequest, UpdateUserRequest> userService;
    private final Scanner scanner;

    private CarMenuCommand(
            Service<User, CreateUserRequest, UpdateUserRequest> userService,
            Service<Car, CreateCarRequest, UpdateCarRequest> carService,
            Scanner scanner) {
        this.userService = userService;
        this.carService = carService;
        this.scanner = scanner;
    }

    public static synchronized CarMenuCommand getInstance(ServiceInjector serviceInjector, Scanner scanner) {
        if (instance == null) {
            instance = new CarMenuCommand(
                    serviceInjector.injectService(ServiceKey.USER_SERVICE),
                    serviceInjector.injectService(ServiceKey.CAR_SERVICE),
                    scanner
            );
        }
        return instance;
    }

    public static synchronized CarMenuCommand getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CarMenuCommand не был инициализирован");
        }
        return instance;
    }

    @Override
    public Command execute() {
        showMenu();
        int choice = getMenuChoice();
        return handleChoice(choice);
    }

    private void showMenu() {
        System.out.println("\n=== Меню поездок ===");
        System.out.println("1. Создать автомобиль");
        System.out.println("2. Обновить автомобиль");
        System.out.println("3. Получить автомобиль");
        System.out.println("4. Получить все автомобили");
        System.out.println("5. Удалить автомобиль");
        System.out.println("0. Назад в главное меню");
        System.out.print("Выберите пункт: ");
    }

    private int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private Command handleChoice(int choice) {
        return switch (choice) {
            case 1 -> CreateCarCommand.getInstance(userService, carService, scanner);
            case 2 -> UpdateCarCommand.getInstance(carService, scanner);
            case 3 -> ReadCarCommand.getInstance(carService, scanner);
            case 4 -> ListCarCommand.getInstance(carService);
            case 5 -> DeleteCarCommand.getInstance(carService, scanner);
            case 0 -> MainMenuCommand.getInstance();
            default -> {
                System.out.println("Неверный выбор. Попробуйте снова.");
                yield this;
            }
        };
    }
}
