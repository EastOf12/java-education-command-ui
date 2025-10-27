package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.trip.TripStatus;
import com.example.crudapp.entites.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UpdateTripCommand implements Command {
    private static UpdateTripCommand instance;
    private final Service<Trip> tripService;
    private final Service<User> userService;
    private final Scanner scanner;

    private UpdateTripCommand(Service<Trip> tripService, Service<User> userService, Scanner scanner) {
        this.tripService = tripService;
        this.userService = userService;
        this.scanner = scanner;
    }

    public static synchronized UpdateTripCommand getInstance(
            Service<Trip> tripService,
            Service<User> userService,
            Scanner scanner) {
        if (instance == null) {
            instance = new UpdateTripCommand(tripService, userService, scanner);
        }

        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Обновление поездки ===");
        System.out.print("Введите ID поездки: ");

        try {
            Long id = Long.parseLong(scanner.nextLine());
            Trip trip = tripService.getById(id);
            if (trip != null) {
                System.out.println("Текущие данные: " + trip);

                System.out.print("Введите id нового водителя (или Enter для пропуска): ");
                String driverId = scanner.nextLine();
                if (!driverId.isEmpty()) {
                    trip.setDriver(userService.getById(Long.valueOf(driverId)));
                }

                System.out.print("Введите новое описание поездки (или Enter для пропуска): ");
                String description = scanner.nextLine();
                if (!description.isEmpty()) {
                    trip.setDescription(description);
                }

                System.out.print("Введите новое количество мест (или Enter для пропуска): ");
                String seats = scanner.nextLine();
                if (!seats.isEmpty()) {
                    trip.setSeats(Integer.parseInt(seats));
                }

                System.out.print("Введите новую стоимость поездки (или Enter для пропуска): ");
                String cost = scanner.nextLine();
                if (!cost.isEmpty()) {
                    trip.setCost(Integer.parseInt(cost));
                }

                System.out.print("Введите новый статус поездки (или Enter для пропуска): ");
                String status = scanner.nextLine();
                if (!status.isEmpty()) {
                    TripStatus tripStatus = new TripStatus(status);
                    trip.setTripStatus(tripStatus);
                }

                System.out.print("Введите новое планируемое время начала поездки в формате dd/MM/yyyy HH:mm: (или Enter для пропуска): ");
                String departure = scanner.nextLine();
                if (!departure.isEmpty()) {
                    trip.getTripScheduling().setPlanedDepartureDateTime(LocalDateTime.parse(departure, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                }

                System.out.print("Введите новое планируемое время завершения поездки в формате dd/MM/yyyy HH:mm: (или Enter для пропуска): ");
                String arrival = scanner.nextLine();
                if (!arrival.isEmpty()) {
                    trip.getTripScheduling().setPlanedArrivalDateTime(LocalDateTime.parse(arrival, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                }

                if(tripService.update(trip)) {
                    System.out.println("Поездка обновлена");
                } else {
                    System.out.println("Поездка не обновлена");
                }
            } else {
                System.out.println("Поездка не найдена");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }

        return TripMenuCommand.getInstance().execute();
    }
}
