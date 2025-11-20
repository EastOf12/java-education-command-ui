package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.commands.ExitCommand;
import com.example.crudapp.commands.MainMenuCommand;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.requests.passenger.CreatePassengerRequest;
import com.example.crudapp.requests.passenger.UpdatePassengerRequest;
import com.example.crudapp.requests.trip.CreateTripRequest;
import com.example.crudapp.requests.trip.UpdateTripRequest;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;
import com.example.crudapp.services.ServiceInjector;
import com.example.crudapp.services.ServiceKey;

import java.util.Scanner;

public class PassengerMenuCommand implements Command {
    private static PassengerMenuCommand instance;
    private final Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService;
    private final Service<User, CreateUserRequest, UpdateUserRequest> userService;
    private final Service<Trip, CreateTripRequest, UpdateTripRequest> tripService;
    private final Scanner scanner;

    private PassengerMenuCommand(
            Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService,
            Service<User, CreateUserRequest, UpdateUserRequest> userService,
            Service<Trip, CreateTripRequest, UpdateTripRequest> tripService,
            Scanner scanner) {
        this.tripService = tripService;
        this.userService = userService;
        this.passengerService = passengerService;
        this.scanner = scanner;
    }

    public static synchronized PassengerMenuCommand getInstance(ServiceInjector serviceInjector, Scanner scanner) {
        if (instance == null) {
            instance = new PassengerMenuCommand(
                    serviceInjector.injectService(ServiceKey.PASSENGER_SERVICE),
                    serviceInjector.injectService(ServiceKey.USER_SERVICE),
                    serviceInjector.injectService(ServiceKey.TRIP_SERVICE),
                    scanner
            );
        }
        return instance;
    }

    public static synchronized PassengerMenuCommand getInstance() {
        if (instance == null) {
            throw new IllegalStateException("PassengerMenuCommand не был инициализирован");
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
        System.out.println("1. Показать всех пассажиров");
        System.out.println("2. Создать пассажира");
        System.out.println("3. Просмотреть пассажира");
        System.out.println("4. Обновить пассажира");
        System.out.println("5. Удалить пассажира");
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
            case 1 -> ListPassengerCommand.getInstance(passengerService);
            case 2 -> CreatePassengerCommand.getInstance(passengerService, scanner);
            case 3 -> ReadPassengerCommand.getInstance(passengerService, scanner);
            case 4 -> UpdatePassengerCommand.getInstance(passengerService, scanner);
            case 5 -> DeletePassengerCommand.getInstance(passengerService, scanner);
            case 0 -> MainMenuCommand.getInstance();
            default -> {
                System.out.println("Неверный выбор. Попробуйте снова.");
                yield this;
            }
        };
    }
}
