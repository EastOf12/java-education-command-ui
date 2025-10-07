package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;

import java.util.Scanner;

public class DeleteTripCommand implements Command {
    private final Service<Trip> tripService;
    private final Scanner scanner;

    public DeleteTripCommand(Service<Trip> tripService, Scanner scanner) {
        this.tripService = tripService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("=== Удаление поездки ===");
        System.out.print("Введите ID поездки: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Trip trip = tripService.getById(id);
            if (trip != null) {
                System.out.println("Удаление поездки: " + trip);
                System.out.print("Подтвердите удаление (y/n): ");
                String confirm = scanner.nextLine();
                if ("y".equalsIgnoreCase(confirm)) {
                    tripService.delete(id);
                    System.out.println("Поездка удалена");
                } else {
                    System.out.println("Удаление отменено");
                }
            } else {
                System.out.println("Поездка не найдена");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }
    }
}
