package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.passanger.Passenger;


import java.util.Scanner;

public class DeletePassengerCommand implements Command {
    private final Service<Passenger> passengerService;
    private final Scanner scanner;

    public DeletePassengerCommand(Service<Passenger> passengerService, Scanner scanner) {
        this.passengerService = passengerService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("=== Удаление пассажира ===");
        System.out.print("Введите ID пассажира: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Passenger passenger = passengerService.getById(id);
            if (passenger != null) {
                System.out.println("Удаление пассажира: " + passenger);
                System.out.print("Подтвердите удаление (y/n): ");
                String confirm = scanner.nextLine();
                if ("y".equalsIgnoreCase(confirm)) {
                    passengerService.delete(id);
                    System.out.println("Пассажир удален");
                } else {
                    System.out.println("Удаление отменено");
                }
            } else {
                System.out.println("Пассажир не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }
    }
}
