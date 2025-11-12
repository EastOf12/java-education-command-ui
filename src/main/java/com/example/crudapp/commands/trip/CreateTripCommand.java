package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.InteractiveBuilder;
import com.example.crudapp.builder.RequestBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.trip.TripScheduling;
import com.example.crudapp.entites.trip.TripStatus;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.requests.trip.CreateTripRequest;
import com.example.crudapp.requests.trip.UpdateTripRequest;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateTripCommand implements Command {
    private static CreateTripCommand instance;
    private final Service<Trip, CreateTripRequest, UpdateTripRequest> tripService;
    private final Service<User, CreateUserRequest, UpdateUserRequest> userService;
    private final Scanner scanner;
    private final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private CreateTripCommand(Service<Trip, CreateTripRequest, UpdateTripRequest> tripService,
                              Service<User, CreateUserRequest, UpdateUserRequest> userService, Scanner scanner) {
        this.tripService = tripService;
        this.userService = userService;
        this.scanner = scanner;
    }

    public static synchronized CreateTripCommand getInstance(
            Service<Trip, CreateTripRequest, UpdateTripRequest> tripService,
            Service<User, CreateUserRequest, UpdateUserRequest> userService,
            Scanner scanner) {
        if (instance == null) {
            instance = new CreateTripCommand(tripService, userService, scanner);
        }
        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Создание поездки ===");


        CreateTripRequest createTripRequest = new RequestBuilder<>(CreateTripRequest::new)
                .addField("Введите id пользователя, который создает поездку: ", (sc, c) -> {
                    try {
                        Long creatorId = Long.valueOf(sc.nextLine());
                        if (userService.getById(creatorId) == null) {
                            System.out.println("Пользователь с ID " + creatorId + " не найден.");
                        }
                        c.setCreatorId(creatorId);
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат ID создателя.");
                    }
                })
                .addField("Введите id водителя, если он уже есть (или Enter для пропуска): ", (sc, c) -> {
                    String input = sc.nextLine();
                    if (!input.isEmpty()) {
                        try {
                            Long driverId = Long.valueOf(input);
                            if ( userService.getById(driverId) == null) {
                                System.out.println("Водитель с ID " + driverId + " не найден.");
                            }
                            c.setDriverId(driverId);
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный формат ID водителя.");
                        }
                    }
                })
                .addField("Введите описание поездки: ", (sc, c) -> c.setDescription(sc.nextLine()))
                .addField("Укажите количество свободных мест: ", (sc, c) -> {
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
                    c.setSeats(seats);
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
                .addField("Укажите планируемую дату и время поездки в формате " + DATE_TIME_FORMAT, (sc,c) -> {
                    c.getTripScheduling().setPlanedArrivalDateTime(readLocalDateTime(sc));
                })
                .addField("Укажите планируемую дату и время прибытия в формате " + DATE_TIME_FORMAT, (sc,c) -> {
                    c.getTripScheduling().setPlanedDepartureDateTime(readLocalDateTime(sc));
                })
                .build(scanner);


        Trip trip = tripService.save(createTripRequest);

        if(trip != null) {
            System.out.println("Поездка создана с ID: " + trip.getId());
            System.out.println(trip);
        }

        return TripMenuCommand.getInstance().execute();
    }

    private LocalDateTime readLocalDateTime(Scanner scanner) {
        LocalDateTime dateTime = null;
        while (dateTime == null) {
            try {
                String input = scanner.nextLine();
                dateTime = LocalDateTime.parse(input, DATE_TIME_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: неверный формат даты. Пожалуйста, введите дату в формате " + DATE_TIME_FORMAT);
            }
        }
        return dateTime;
    }
}
