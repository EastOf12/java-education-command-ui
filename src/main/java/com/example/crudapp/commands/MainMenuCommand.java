package com.example.crudapp.commands;

import com.example.crudapp.commands.car.CarMenuCommand;
import com.example.crudapp.commands.passenger.PassengerMenuCommand;
import com.example.crudapp.commands.trip.TripMenuCommand;
import com.example.crudapp.commands.user.UserMenuCommand;
import com.example.crudapp.services.ServiceInjector;

import java.util.Scanner;

public class MainMenuCommand implements Command {
    private static MainMenuCommand instance;
    private final Scanner scanner;
    private final ServiceInjector serviceInjector;

    private MainMenuCommand(ServiceInjector serviceInjector, Scanner scanner) {
        this.serviceInjector = serviceInjector;
        this.scanner = scanner;
    }

    public static synchronized MainMenuCommand getInstance(ServiceInjector serviceInjector, Scanner scanner) {
        if (instance == null) {
            instance = new MainMenuCommand(serviceInjector, scanner);
        }
        return instance;
    }

    public static synchronized MainMenuCommand getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MainMenuCommand не был инициализирован");
        }

        return instance;
    }

    @Override
    public Command execute() {
        showMenu();
        return handleChoice(getMenuChoice());
    }

    private void showMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Работа с пользователями");
        System.out.println("2. Работа с поездками");
        System.out.println("3. Работа с пассажирами");
        System.out.println("4. Работа с автомобилями");
        System.out.println("0. Выход");
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
            case 1 -> UserMenuCommand.getInstance(serviceInjector, scanner);
            case 2 -> TripMenuCommand.getInstance(serviceInjector, scanner);
            case 3 -> PassengerMenuCommand.getInstance(serviceInjector, scanner);
            case 4 -> CarMenuCommand.getInstance(serviceInjector, scanner);
            case 0 -> ExitCommand.getInstance(scanner);
            default -> {
                System.out.println("Неверный выбор. Попробуйте снова.");
                yield this;
            }
        };
    }
}
