package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.RequestBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.passanger.PassengerStatus;
import com.example.crudapp.requests.passenger.CreatePassengerRequest;
import com.example.crudapp.requests.passenger.UpdatePassengerRequest;

import java.util.Scanner;

public class UpdatePassengerCommand implements Command {
    private static UpdatePassengerCommand instance;
    private final Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService;
    private final Scanner scanner;

    private UpdatePassengerCommand(Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService, Scanner scanner) {
        this.passengerService = passengerService;
        this.scanner = scanner;
    }

    public static synchronized UpdatePassengerCommand getInstance(
            Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService,
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

                UpdatePassengerRequest updatePassengerRequest = new RequestBuilder<>(UpdatePassengerRequest::new)
                        .addField("Введите новый статус пассажира (или Enter для пропуска): ", (sc, c) -> {
                            String status = sc.nextLine();

                            if (!status.isEmpty()) {
                                c.setPassengerStatus(new PassengerStatus(status));
                            }
                        })
                        .addField("Введите новое количество мест (или Enter для пропуска): ", (sc, c) -> {
                            String seats = sc.nextLine();

                            if (!seats.isEmpty()) {
                                passenger.setSeats(Integer.parseInt(seats));
                            }
                        })
                        .build(scanner);

                if (passengerService.update(id, updatePassengerRequest) != null) {
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

        return PassengerMenuCommand.getInstance();
    }
}
