package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.requests.trip.CreateTripRequest;
import com.example.crudapp.requests.trip.UpdateTripRequest;

import java.util.Scanner;


public class ReadTripCommand implements Command {
    private static ReadTripCommand instance;
    private final Service<Trip, CreateTripRequest, UpdateTripRequest> tripService;
    private final Scanner scanner;

    private ReadTripCommand(Service<Trip, CreateTripRequest, UpdateTripRequest> tripService, Scanner scanner) {
        this.tripService = tripService;
        this.scanner = scanner;
    }

    public static synchronized ReadTripCommand getInstance(Service<Trip, CreateTripRequest, UpdateTripRequest> tripService, Scanner scanner) {
        if (instance == null) {
            instance = new ReadTripCommand(tripService, scanner);
        }
        return instance;
    }

    @Override
    public Command execute() {
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

        return TripMenuCommand.getInstance();
    }
}
