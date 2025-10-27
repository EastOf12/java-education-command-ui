package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.commands.MainMenuCommand;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.services.ServiceInjector;
import com.example.crudapp.services.ServiceKey;

import java.util.Scanner;

public class TripMenuCommand implements Command {
    private static TripMenuCommand instance;
    private final Service<Trip> tripService;
    private final Service<User> userService;
    private final Scanner scanner;

    private TripMenuCommand(Service<Trip> tripService, Service<User> userService, Scanner scanner) {
        this.userService = userService;
        this.tripService = tripService;
        this.scanner = scanner;
    }

    public static synchronized TripMenuCommand getInstance(ServiceInjector serviceInjector, Scanner scanner) {
        if (instance == null) {
            instance = new TripMenuCommand(
                    serviceInjector.injectService(ServiceKey.TRIP_SERVICE),
                    serviceInjector.injectService(ServiceKey.USER_SERVICE),
                    scanner
            );
        }

        return instance;
    }

    public static synchronized TripMenuCommand getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TripMenuCommand не был инициализирован");
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
        System.out.println("1. Показать все поездки");
        System.out.println("2. Создать поездку");
        System.out.println("3. Просмотреть поездку");
        System.out.println("4. Обновить поездку");
        System.out.println("5. Удалить поездку");
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
            case 1 -> ListTripCommand.getInstance(tripService).execute();
            case 2 -> CreateTripCommand.getInstance(tripService, userService, scanner).execute();
            case 3 -> ReadTripCommand.getInstance(tripService, scanner).execute();
            case 4 -> UpdateTripCommand.getInstance(tripService, userService, scanner).execute();
            case 5 -> DeleteTripCommand.getInstance(tripService, scanner).execute();
            case 0 -> MainMenuCommand.getInstance().execute();
            default -> {
                System.out.println("Неверный выбор. Попробуйте снова.");
                yield this.execute();
            }
        };
    }
}
