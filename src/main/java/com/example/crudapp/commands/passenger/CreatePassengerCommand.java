package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.InteractiveBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.passanger.PassengerStatus;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.user.User;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CreatePassengerCommand implements Command {
    private static CreatePassengerCommand instance;
    private final Service<Passenger> passengerService;
    private final Service<User> userService;
    private final Service<Trip> tripService;
    private final Scanner scanner;

    private CreatePassengerCommand(
            Service<Passenger> passengerService,
            Service<User> userService,
            Service<Trip> tripService,
            Scanner scanner) {
        this.passengerService = passengerService;
        this.userService = userService;
        this.tripService = tripService;
        this.scanner = scanner;
    }

    public static synchronized CreatePassengerCommand getInstance(
            Service<Passenger> passengerService,
            Service<User> userService,
            Service<Trip> tripService,
            Scanner scanner) {
        if (instance == null) {
            instance = new CreatePassengerCommand(passengerService, userService, tripService, scanner);
        }

        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Создание пассажира ===");

        InteractiveBuilder<Passenger> builder = new InteractiveBuilder<>(Passenger::new);
        builder
                .addField("Введите id пользователя, который хочет стать пассажиром: ",
                        (scanner, passenger) -> passenger.setUser(userService.getById(Long.valueOf(scanner.nextLine()))))
                .addField("Введите id поездки, к которой хочет присоединиться пассажир: ",
                        (scanner, passenger) -> passenger.setTrip(tripService.getById(Long.valueOf(scanner.nextLine()))))
                .addField("Укажите необходимое количество мест: ",
                        (scanner, passenger) -> {
                            int seats = 0;
                            while (seats < 1) {
                                try {
                                    seats = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Количество мест указано не верно. Укажите повторно");
                                }
                            }
                            passenger.setSeats(seats);
                        })
                .addField("Укажите сообщение для водителя: ",
                        (scanner, passenger) -> passenger.setMessage(scanner.nextLine()));

        Passenger passenger = builder.build(scanner);
        passenger.setCreatedAt(LocalDateTime.now());

        PassengerStatus passengerStatus = new PassengerStatus();
        passengerStatus.setStatus("new");
        passenger.setPassengerStatus(passengerStatus);

        passengerService.save(passenger);
        System.out.println("Пассажир создан с ID: " + passenger.getId());
        System.out.println(passenger);

        return PassengerMenuCommand.getInstance().execute();
    }
}
