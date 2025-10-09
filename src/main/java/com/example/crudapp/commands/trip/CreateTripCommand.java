package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
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
    private final Service<Trip> tripService;
    private final Service<User> userService;
    private final Scanner scanner;

    public CreateTripCommand(Service<Trip> tripService, Service<User> userService, Scanner scanner) {
        this.tripService = tripService;
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        Trip trip = new Trip();
        trip.setCreatedAt(LocalDateTime.now());

        System.out.println("=== Создание поездки ===");
        System.out.print("Введите id пользователя, который создает поездку: ");
        trip.setCreator(userService.getById(Long.valueOf(scanner.nextLine())));

        System.out.print("Введите id водителя, если он уже есть (или Enter для пропуска): ");
        String idDriver = scanner.nextLine();

        if (!idDriver.isEmpty()) {
            trip.setDriver(userService.getById(Long.valueOf(idDriver)));
        }

        System.out.print("Введите описание поездки: ");
        trip.setDescription(scanner.nextLine());

        System.out.print("Укажите количество свободных мест: ");
        int seat = 0;

        while (seat < 1) {
            try {
                seat = Integer.parseInt(scanner.nextLine());
                trip.setSeats(seat);
            } catch (NumberFormatException e) {
                System.out.println("Число мест указано не верно. Укажите повторно");
            }
        }

        System.out.print("Укажите стоимость поездки: ");

        int cost = 0;

        while (cost < 1) {
            try {
                cost = Integer.parseInt(scanner.nextLine());
                trip.setCost(cost);
            } catch (NumberFormatException e) {
                System.out.println("Стоимость поездки указана не верно. Укажите повторно");
            }
        }

        trip.setTripStatus(new TripStatus("new"));

        System.out.print("Укажите планируемую дату и время поездки в формате dd/MM/yyyy HH:mm: ");
        // Определение формата даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime departureDateTime = null;

        while (departureDateTime == null) {
            try {
                String input = scanner.nextLine();

                // Преобразование введённой строки в LocalDate
                departureDateTime = LocalDateTime.parse(input, formatter);

            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: неверный формат даты. Пожалуйста, введите дату в формате dd/MM/yyyy HH:mm ");
            }
        }

        System.out.print("Укажите планируемую дату и время прибытия в формате dd/MM/yyyy HH:mm: ");
        LocalDateTime arrivalDateTime = null;

        while (arrivalDateTime == null) {
            try {
                String input = scanner.nextLine();

                // Преобразование введённой строки в LocalDate
                arrivalDateTime = LocalDateTime.parse(input, formatter);

            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: неверный формат даты. Пожалуйста, введите дату в формате dd/MM/yyyy HH:mm ");
            }
        }

        TripScheduling tripScheduling = new TripScheduling(departureDateTime, arrivalDateTime);
        trip.setTripScheduling(tripScheduling);

        tripService.save(trip);
        System.out.println("Поездка создана создана с ID: " + trip.getId());
        System.out.println(trip);
    }
}
