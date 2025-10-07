package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.passanger.Passenger;


public class ListPassengerCommand implements Command {

    private final Service<Passenger> passengerService;

    public ListPassengerCommand(Service<Passenger> passengerService) {
        this.passengerService = passengerService;
    }

    @Override
    public void execute() {
        System.out.println("=== Список пассажиров ===");
        java.util.List<Passenger> passengers = passengerService.getAll();
        if (passengers.isEmpty()) {
            System.out.println("Нет пассажиров");
        } else {
            passengers.forEach(System.out::println);
        }
    }
}
