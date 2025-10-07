package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.user.User;

import java.util.Scanner;

public class TripMenuCommand implements Command {
    private final Service<Trip> tripService;
    private final Service<User> userService;
    private final Scanner scanner;
    private boolean running = true;

    public TripMenuCommand(Service<Trip> tripService, Service<User> userService, Scanner scanner) {
        this.userService = userService;
        this.tripService = tripService;
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

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                new ListTripCommand(tripService).execute();
                break;
            case 2:
                new CreateTripCommand(tripService, userService, scanner).execute();
                break;
            case 3:
                new ReadTripCommand(tripService, scanner).execute();
                break;
            case 4:
                new UpdateTripCommand(tripService, userService, scanner).execute();
                break;
            case 5:
                new DeleteTripCommand(tripService, scanner).execute();
                break;
            case 0:
                running = false;
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}
