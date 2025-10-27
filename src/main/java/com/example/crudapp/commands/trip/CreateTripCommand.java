package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.InteractiveBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.trip.TripScheduling;
import com.example.crudapp.entites.trip.TripStatus;
import com.example.crudapp.entites.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateTripCommand implements Command {
    private static CreateTripCommand instance;
    private final Service<Trip> tripService;
    private final Service<User> userService;
    private final Scanner scanner;

    private CreateTripCommand(Service<Trip> tripService, Service<User> userService, Scanner scanner) {
        this.tripService = tripService;
        this.userService = userService;
        this.scanner = scanner;
    }

    public static synchronized CreateTripCommand getInstance(
            Service<Trip> tripService,
            Service<User> userService,
            Scanner scanner) {
        if (instance == null) {
            instance = new CreateTripCommand(tripService, userService, scanner);
        }
        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Создание поездки ===");

        Trip trip = new InteractiveBuilder<>(Trip::new)
                .addField("Введите id пользователя, который создает поездку: ", (sc, t) -> {
                    try {
                        Long creatorId = Long.valueOf(sc.nextLine());
                        User creator = userService.getById(creatorId);
                        if (creator == null) {
                            System.out.println("Пользователь-создатель с ID " + creatorId + " не найден.");
                        }
                        t.setCreator(creator);
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат ID создателя.");
                        t.setCreator(null);
                    }
                })
                .addField("Введите id водителя, если он уже есть (или Enter для пропуска): ", (sc, t) -> {
                    String input = sc.nextLine();
                    if (!input.isEmpty()) {
                        try {
                            Long driverId = Long.valueOf(input);
                            User driver = userService.getById(driverId);
                            if (driver == null) {
                                System.out.println("Водитель с ID " + driverId + " не найден.");
                            }
                            t.setDriver(driver);
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный формат ID водителя.");
                            t.setDriver(null);
                        }
                    }
                })
                .addField("Введите описание поездки: ", (sc, t) -> t.setDescription(sc.nextLine()))
                .addField("Укажите количество свободных мест: ", (sc, t) -> {
                    int seats = 0;
                    while (seats < 1) {
                        try {
                            seats = Integer.parseInt(sc.nextLine());
                            if (seats < 1) {
                                System.out.println("Количество мест должно быть ≥ 1. Повторите ввод:");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный формат. Введите целое число ≥ 1:");
                            seats = 0;
                        }
                    }
                    t.setSeats(seats);
                })
                .addField("Укажите стоимость поездки: ", (sc, t) -> {
                    int cost = 0;
                    while (cost < 1) {
                        try {
                            cost = Integer.parseInt(sc.nextLine());
                            if (cost < 1) {
                                System.out.println("Стоимость должна быть ≥ 1. Повторите ввод:");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный формат. Введите целое число ≥ 1:");
                            cost = 0;
                        }
                    }
                    t.setCost(cost);
                })
                .build(scanner);

        trip.setCreatedAt(LocalDateTime.now());

        TripStatus status = new TripStatus("new");
        trip.setTripStatus(status);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime departure = readLocalDateTime(scanner,
                "Укажите планируемую дату и время поездки в формате dd/MM/yyyy HH:mm: ", formatter);
        LocalDateTime arrival = readLocalDateTime(scanner,
                "Укажите планируемую дату и время прибытия в формате dd/MM/yyyy HH:mm: ", formatter);

        trip.setTripScheduling(new TripScheduling(departure, arrival));

        tripService.save(trip);
        System.out.println("Поездка создана с ID: " + trip.getId());
        System.out.println(trip);

        return TripMenuCommand.getInstance().execute();
    }

    private LocalDateTime readLocalDateTime(Scanner scanner, String prompt, DateTimeFormatter formatter) {
        LocalDateTime dateTime = null;
        while (dateTime == null) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                dateTime = LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: неверный формат даты. Пожалуйста, введите дату в формате dd/MM/yyyy HH:mm");
            }
        }
        return dateTime;
    }
}
