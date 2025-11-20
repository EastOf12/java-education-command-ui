package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.commands.Confirm;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.requests.trip.CreateTripRequest;
import com.example.crudapp.requests.trip.UpdateTripRequest;

import java.util.Scanner;

public class DeleteTripCommand implements Command {
    private static DeleteTripCommand instance;
    private final Service<Trip, CreateTripRequest, UpdateTripRequest> tripService;
    private final Scanner scanner;

    private DeleteTripCommand(
            Service<Trip, CreateTripRequest, UpdateTripRequest> tripService,
            Scanner scanner) {
        this.tripService = tripService;
        this.scanner = scanner;
    }

    public static synchronized DeleteTripCommand getInstance(Service<Trip, CreateTripRequest, UpdateTripRequest> tripService, Scanner scanner) {
        if (instance == null) {
            instance = new DeleteTripCommand(tripService, scanner);
        }

        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Удаление поездки ===");
        System.out.print("Введите ID поездки: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Trip trip = tripService.getById(id);
            if (trip != null) {
                System.out.println("Удаление поездки: " + trip);
                System.out.print("Подтвердите удаление (y/n): ");
                String confirm = scanner.nextLine();
                if (Confirm.valueOf(confirm).equals(Confirm.y)) {
                    if(tripService.delete(id)) {
                        System.out.println("Поездка удалена");
                    } else {
                        System.out.println("Ошибка при удалении поездки");
                    }
                } else {
                    System.out.println("Удаление отменено");
                }
            } else {
                System.out.println("Поездка не найдена");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }

        return TripMenuCommand.getInstance();
    }
}
