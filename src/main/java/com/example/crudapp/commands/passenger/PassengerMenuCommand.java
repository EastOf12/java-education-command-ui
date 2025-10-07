package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.user.User;

import java.util.Scanner;

public class PassengerMenuCommand implements Command {
    private final Service<Passenger> passengerService;
    private final Service<User> userService;
    private final Service<Trip> tripService;
    private final Scanner scanner;
    private boolean running = true;

    public PassengerMenuCommand(
            Service<Passenger> passengerService,
            Service<User> userService,
            Service<Trip> tripService,
            Scanner scanner) {
        this.tripService = tripService;
        this.userService = userService;
        this.passengerService = passengerService;
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

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                new ListPassengerCommand(passengerService).execute();
                break;
            case 2:
                new CreatePassengerCommand(passengerService, userService, tripService, scanner).execute();
                break;
            case 3:
                new ReadPassengerCommand(passengerService, scanner).execute();
                break;
            case 4:
                new UpdatePassengerCommand(passengerService, scanner).execute();
                break;
            case 5:
                new DeletePassengerCommand(passengerService, scanner).execute();
                break;
            case 0:
                running = false;
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}
