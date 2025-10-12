package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.entites.user.User;

import java.util.Scanner;

public class CarMenuCommand implements Command {
    private final Service<Car> carService;
    private final Service<User> userService;
    private final Scanner scanner;
    private boolean running = true;

    public CarMenuCommand(
            Service<User> userService,
            Service<Car> carService,
            Scanner scanner) {
        this.userService = userService;
        this.carService = carService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        running = true;
        while (running) {
            showMenu();
            int choice = getMenuChoice();
            handleChoice(choice);

            if (running && choice != 0) {
                System.out.println("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }
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

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                new CreateCarCommand(userService, carService, scanner).execute();
                break;
            case 2:
                new UpdateCarCommand(carService, scanner).execute();
                break;
            case 3:
                new ReadCarCommand(carService, scanner).execute();
                break;
            case 4:
                new ListCarCommand(carService).execute();
                break;
            case 5:
                new DeleteCarCommand(carService, scanner).execute();
                break;
            case 0:
                running = false;
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}
