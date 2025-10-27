package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.passanger.Passenger;

import java.util.Scanner;

public class ReadPassengerCommand implements Command {

    private static ReadPassengerCommand instance;
    private final Service<Passenger> passengerService;
    private final Scanner scanner;

    private ReadPassengerCommand(Service<Passenger> passengerService, Scanner scanner) {
        this.passengerService = passengerService;
        this.scanner = scanner;
    }

    public static synchronized ReadPassengerCommand getInstance(
            Service<Passenger> passengerService,
            Scanner scanner) {
        if (instance == null) {
            instance = new ReadPassengerCommand(passengerService, scanner);
        }
        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Просмотр пассажира ===");
        System.out.print("Введите ID пассажира: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Passenger passenger = passengerService.getById(id);
            if (passenger != null) {
                System.out.println(passenger);
            } else {
                System.out.println("Пассажир не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }

        return PassengerMenuCommand.getInstance().execute();
    }
}
