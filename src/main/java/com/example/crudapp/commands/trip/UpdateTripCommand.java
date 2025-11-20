package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.RequestBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.trip.TripStatus;
import com.example.crudapp.requests.trip.CreateTripRequest;
import com.example.crudapp.requests.trip.UpdateTripRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UpdateTripCommand implements Command {
    private static UpdateTripCommand instance;
    private final Service<Trip, CreateTripRequest, UpdateTripRequest> tripService;
    private final Scanner scanner;

    private UpdateTripCommand(
            Service<Trip, CreateTripRequest, UpdateTripRequest> tripService,
            Scanner scanner) {
        this.tripService = tripService;
        this.scanner = scanner;
    }

    public static synchronized UpdateTripCommand getInstance(
            Service<Trip, CreateTripRequest, UpdateTripRequest> tripService,
            Scanner scanner) {
        if (instance == null) {
            instance = new UpdateTripCommand(tripService, scanner);
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


                UpdateTripRequest updateTripRequest = new RequestBuilder<>(UpdateTripRequest::new)
                        .addField("Введите id нового водителя (или Enter для пропуска): ", (sc, c) -> {
                            String driverId = sc.nextLine();
                            if (!driverId.isEmpty()) {
                                c.setDriverId(Long.valueOf(driverId));
                            }
                        })
                        .addField("Введите новое описание поездки (или Enter для пропуска): ", (sc, c) -> {
                            String description = sc.nextLine();
                            if (!description.isEmpty()) {
                                c.setDescription(description);
                            }
                        })
                        .addField("Введите новое количество мест (или Enter для пропуска): ", (sc, c) -> {
                            String seats = sc.nextLine();
                            if (!seats.isEmpty()) {
                                try {
                                    c.setSeats(Integer.parseInt(seats));
                                } catch (NumberFormatException e) {
                                    System.out.println("Неверный формат количества мест. Поле не изменено.");
                                }
                            }
                        })
                        .addField("Введите новую стоимость поездки (или Enter для пропуска): ", (sc, c) -> {
                            String cost = sc.nextLine();
                            if (!cost.isEmpty()) {
                                try {
                                    c.setCost(Integer.parseInt(cost));
                                } catch (NumberFormatException e) {
                                    System.out.println("Неверный формат стоимости. Поле не изменено.");
                                }
                            }
                        })
                        .addField("Введите новый статус поездки (или Enter для пропуска): ", (sc, c) -> {
                            String status = sc.nextLine();
                            if (!status.isEmpty()) {
                                TripStatus tripStatus = new TripStatus(status);
                                c.setTripStatus(tripStatus);
                            }
                        })
                        .addField(
                                "Введите новое планируемое время начала поездки в формате dd/MM/yyyy HH:mm: (или" +
                                        " Enter для пропуска): ", (sc, c) -> {
                                    String departure = sc.nextLine();
                                    if (!departure.isEmpty()) {
                                        c.getTripScheduling().setPlanedDepartureDateTime(LocalDateTime.parse(departure,
                                                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                                    }
                                })
                        .addField(
                                "Введите новое планируемое время завершения поездки в формате dd/MM/yyyy HH:mm:" +
                                        " (или Enter для пропуска): ", (sc, c) -> {
                                    String arrival = sc.nextLine();
                                    if (!arrival.isEmpty()) {
                                        c.getTripScheduling().setPlanedArrivalDateTime(LocalDateTime.parse(arrival,
                                                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                                    }
                                })
                        .build(scanner);

                if (tripService.update(id, updateTripRequest) != null) {
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

        return TripMenuCommand.getInstance();
    }
}
