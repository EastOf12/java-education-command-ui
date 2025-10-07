package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;

import java.util.Scanner;


public class ReadTripCommand implements Command {

    private final Service<Trip> tripService;
    private final Scanner scanner;

    public ReadTripCommand(Service<Trip> tripService, Scanner scanner) {
        this.tripService = tripService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("=== Просмотр поездки ===");
        System.out.print("Введите ID поездки: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Trip trip = tripService.getById(id);
            if (trip != null) {
                System.out.println(trip);
            } else {
                System.out.println("Поездка не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }
    }
}
