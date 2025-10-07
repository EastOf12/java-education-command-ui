package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.trip.TripScheduling;
import com.example.crudapp.entites.trip.TripStatus;
import com.example.crudapp.entites.user.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UpdateTripCommand implements Command {
    private final Service<Trip> tripService;
    private final Service<User> userService;
    private final Scanner scanner;

    public UpdateTripCommand(Service<Trip> tripService, Service<User> userService, Scanner scanner) {
        this.tripService = tripService;
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("=== Обновление поездки ===");
        System.out.print("Введите ID поездки: ");

        try {
            Long id = Long.parseLong(scanner.nextLine());
            Trip tripOld = tripService.getById(id);
            if (tripOld != null) {
                System.out.println("Текущие данные: " + tripOld);

                Trip trip = new Trip();
                trip.setCreatedAt(tripOld.getCreatedAt());

                System.out.print("Введите id нового водителя (или Enter для пропуска): ");
                String driverId = scanner.nextLine();
                if (!driverId.isEmpty()) {
                    trip.setDriver(userService.getById(Long.valueOf(driverId)));
                } else {
                    trip.setDriver(tripOld.getDriver());
                }

                System.out.print("Введите новое описание поездки (или Enter для пропуска): ");
                String description = scanner.nextLine();
                if (!description.isEmpty()) {
                    trip.setDescription(description);
                } else {
                    trip.setDescription(tripOld.getDescription());
                }

                System.out.print("Введите новое количество мест (или Enter для пропуска): ");
                String seats = scanner.nextLine();

                if(!seats.isEmpty()) {
                    trip.setSeats(Integer.parseInt(seats));
                } else {
                    trip.setSeats(tripOld.getSeats());
                }

                System.out.print("Введите новую стоимость поездки (или Enter для пропуска): ");
                String cost = scanner.nextLine();

                if(!cost.isEmpty()) {
                    trip.setCost(Integer.parseInt(cost));
                } else {
                    trip.setCost(tripOld.getCost());
                }

                System.out.print("Введите новый статус поездки (или Enter для пропуска): ");
                String status = scanner.nextLine();

                if(!status.isEmpty()) {
                    TripStatus tripStatus = new TripStatus();
                    tripStatus.setStatus(status);
                    tripStatus.setTrip(trip);
                    tripStatus.setId(trip.getTripStatus().getId());
                    trip.setTripStatus(tripStatus);
                } else {
                    trip.setTripStatus(tripOld.getTripStatus());
                }

                System.out.print("Введите новое планируемое время начала поездки в формате dd/MM/yyyy HH:mm: (или Enter для пропуска): ");
                String departure = scanner.nextLine();

                System.out.print("Введите новое планируемое время завершения поездки в формате dd/MM/yyyy HH:mm: (или Enter для пропуска): ");
                String arrival = scanner.nextLine();

                if(!departure.isEmpty() || !arrival.isEmpty()) {
                    TripScheduling tripScheduling = new TripScheduling();
                    tripScheduling.setTrip(trip);
                    tripScheduling.setId(trip.getTripScheduling().getId());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                    if(!departure.isEmpty()) {
                        try {
                        tripScheduling.setPlanedDepartureDateTime(LocalDateTime.parse(departure, formatter));
                        } catch (DateTimeParseException e) {
                            System.out.println("Ошибка: неверный формат даты");
                        }
                    }

                    if(!arrival.isEmpty()) {
                        try {
                            tripScheduling.setPlanedArrivalDateTime(LocalDateTime.parse(arrival, formatter));
                        } catch (DateTimeParseException e) {
                            System.out.println("Ошибка: неверный формат даты");
                        }
                    }

                    if(tripScheduling.getPlanedDepartureDateTime() == null) {
                        tripScheduling.setPlanedDepartureDateTime(trip.getTripScheduling().getPlanedDepartureDateTime());
                    }

                    if(tripScheduling.getPlanedArrivalDateTime() == null) {
                        tripScheduling.setPlanedArrivalDateTime(trip.getTripScheduling().getPlanedArrivalDateTime());
                    }

                    trip.setTripScheduling(tripScheduling);
                } else {
                    trip.setTripScheduling(tripOld.getTripScheduling());
                }

                tripService.update(trip);
                System.out.println("Поездка обновлена");
            } else {
                System.out.println("Поездка не найдена");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }
    }
}
