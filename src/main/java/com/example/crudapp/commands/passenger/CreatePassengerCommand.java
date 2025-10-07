package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.passanger.PassengerStatus;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.user.User;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CreatePassengerCommand implements Command {
    private final Service<Passenger> passengerService;
    private final Service<User> userService;
    private final Service<Trip> tripService;
    private final Scanner scanner;

    public CreatePassengerCommand(Service<Passenger> passengerService, Service<User> userService, Service<Trip> tripService, Scanner scanner) {
        this.passengerService = passengerService;
        this.userService = userService;
        this.tripService = tripService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        Passenger passenger = new Passenger();
        passenger.setCreatedAt(LocalDateTime.now());

        System.out.println("=== Создание пассажира ===");
        System.out.print("Введите id пользователя, который хочет стать пассажиром: ");
        passenger.setUser(userService.getById(Long.valueOf(scanner.nextLine())));

        //Ставим первичный статус
        PassengerStatus passengerStatus = new PassengerStatus();
        passengerStatus.setStatus("new");

        passenger.setPassengerStatus(passengerStatus);

        System.out.print("Введите id поездки, к которой хочет присоединиться пассажир: ");
        passenger.setTrip(tripService.getById(Long.valueOf(scanner.nextLine())));

        System.out.print("Укажите необходимое количество мест: ");
        int seats = 0;

        while (seats < 1) {
            try {
                seats = Integer.parseInt(scanner.nextLine());
                passenger.setSeats(seats);
            } catch (NumberFormatException e) {
                System.out.println("Количество мест указано не верно. Укажите повторно");
            }
        }

        System.out.println("Укажите сообщение для водителя");
        passenger.setMessage(scanner.nextLine());

        passengerService.save(passenger);
        System.out.println("Пассажир создан с ID: " + passenger.getId());
        System.out.println(passenger);
    }
}
