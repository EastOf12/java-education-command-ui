package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.commands.Confirm;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.requests.passenger.CreatePassengerRequest;
import com.example.crudapp.requests.passenger.UpdatePassengerRequest;

import java.util.Scanner;

public class DeletePassengerCommand implements Command {
    private static DeletePassengerCommand instance;
    private final Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService;
    private final Scanner scanner;

    private DeletePassengerCommand(Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService, Scanner scanner) {
        this.passengerService = passengerService;
        this.scanner = scanner;
    }

    public static synchronized DeletePassengerCommand getInstance(
            Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService, Scanner scanner) {
        if (instance == null) {
            instance = new DeletePassengerCommand(passengerService, scanner);
        }

        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Удаление пассажира ===");
        System.out.print("Введите ID пассажира: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Passenger passenger = passengerService.getById(id);
            if (passenger != null) {
                System.out.println("Удаление пассажира: " + passenger);
                System.out.print("Подтвердите удаление (y/n): ");
                String confirm = scanner.nextLine();
                if (Confirm.valueOf(confirm).equals(Confirm.y)) {
                    if(passengerService.delete(id)) {
                        System.out.println("Пассажир удален");
                    } else {
                        System.out.println("Ошибка при удалении пассажира");
                    }
                } else {
                    System.out.println("Удаление отменено");
                }
            } else {
                System.out.println("Пассажир не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }

        return PassengerMenuCommand.getInstance();
    }
}
