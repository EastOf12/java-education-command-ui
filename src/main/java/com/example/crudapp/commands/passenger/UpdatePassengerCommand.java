package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.passanger.Passenger;

import java.util.Scanner;

public class UpdatePassengerCommand implements Command {
    private static UpdatePassengerCommand instance;
    private final Service<Passenger> passengerService;
    private final Scanner scanner;

    private UpdatePassengerCommand(Service<Passenger> passengerService, Scanner scanner) {
        this.passengerService = passengerService;
        this.scanner = scanner;
    }

    public static synchronized UpdatePassengerCommand getInstance(
            Service<Passenger> passengerService,
            Scanner scanner) {
        if (instance == null) {
            instance = new UpdatePassengerCommand(passengerService, scanner);
        }

        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Обновление пассажира ===");
        System.out.print("Введите ID пассажира: ");

        try {
            Long id = Long.parseLong(scanner.nextLine());
            Passenger passenger = passengerService.getById(id);

            if (passenger != null) {

                System.out.print("Введите новый статус пассажира (или Enter для пропуска): ");
                String status = scanner.nextLine();

                if (!status.isEmpty()) {
                    passenger.getPassengerStatus().setStatus(status);
                }

                System.out.print("Введите новое количество мест (или Enter для пропуска): ");
                String seats = scanner.nextLine();

                if (!seats.isEmpty()) {
                    passenger.setSeats(Integer.parseInt(seats));
                }

                if(passengerService.update(passenger)) {
                    System.out.println("Пассажир обновлен");
                } else {
                    System.out.println("Пассажир не обновлен");
                }
            } else {
                System.out.println("Пассажир не найден обновлен");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }

        return PassengerMenuCommand.getInstance().execute();
    }
}
